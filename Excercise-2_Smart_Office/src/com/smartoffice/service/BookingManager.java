package com.smartoffice.service;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Booking;
import com.smartoffice.model.Room;
import com.smartoffice.exceptions.BookingException;
import com.smartoffice.util.LoggerUtil;

import java.time.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingManager {
    private static BookingManager instance;

    // now store multiple bookings per room
    private final Map<Integer, List<Booking>> bookings = new ConcurrentHashMap<>();
    // scheduled tasks keyed by booking.hashCode()
    private final Map<Integer, ScheduledFuture<?>> autoReleaseTasks = new ConcurrentHashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final Logger logger = LoggerUtil.getLogger();

    // configurable window (minutes) to auto-release if not occupied
    private final long autoReleaseWindowMinutes;

    private BookingManager(long autoReleaseWindowMinutes) {
        this.autoReleaseWindowMinutes = autoReleaseWindowMinutes;
    }

    public static synchronized BookingManager getInstance() {
        if (instance == null) instance = new BookingManager(5); // default 5 minutes
        return instance;
    }

    public static synchronized BookingManager getInstanceWithWindow(long mins) {
        if (instance == null) instance = new BookingManager(mins);
        return instance;
    }

    /**
     * Book a room (prevents overlapping bookings for the same room).
     * Uses end-exclusive intervals: [start, end)
     */
    public synchronized Booking bookRoom(int roomId, LocalDateTime start, int durationMinutes, String user) throws BookingException {
        Room r = config.getRoom(roomId);
        if (r == null) throw new BookingException("Room " + roomId + " does not exist.");
        if (durationMinutes <= 0) throw new BookingException("Duration must be positive");

        LocalDateTime end = start.plusMinutes(durationMinutes);

        // get or create the list for this room
        List<Booking> roomBookings = bookings.computeIfAbsent(roomId, k -> new ArrayList<>());

        // check overlap with any active booking
        for (Booking existing : roomBookings) {
            if (existing.isActive() && timesOverlap(existing.getStart(), existing.getEnd(), start, end)) {
                throw new BookingException("Room " + roomId + " is already booked from " +
                        existing.getStart() + " to " + existing.getEnd());
            }
        }

        Booking booking = new Booking(roomId, start, durationMinutes, user);
        roomBookings.add(booking);

        scheduleAutoRelease(booking);

        System.out.println("Booked " + booking);
        logger.fine("Booked " + booking);

        return booking;
    }

    /**
     * End-exclusive overlap check: [aStart, aEnd) overlaps [bStart, bEnd) iff
     * aStart < bEnd && bStart < aEnd
     */
    private boolean timesOverlap(LocalDateTime aStart, LocalDateTime aEnd,
                                 LocalDateTime bStart, LocalDateTime bEnd) {
        return aStart.isBefore(bEnd) && bStart.isBefore(aEnd);
    }

    /**
     * Cancel the currently active booking for a room (old semantics).
     * This tries to find a booking that is active *right now* and cancels it.
     */
    public synchronized void cancelBooking(int roomId) throws BookingException {
        List<Booking> roomBookings = bookings.get(roomId);
        if (roomBookings == null || roomBookings.isEmpty()) {
            throw new BookingException("Room " + roomId + " has no active bookings. Cannot cancel.");
        }

        LocalDateTime now = LocalDateTime.now();
        Booking toCancel = null;
        for (Booking b : roomBookings) {
            if (b.isActive() && !now.isBefore(b.getStart()) && now.isBefore(b.getEnd())) {
                toCancel = b;
                break;
            }
        }

        if (toCancel == null) {
            throw new BookingException("No currently active booking found for room " + roomId);
        }

        doCancelBooking(toCancel);
        logger.fine("Booking for Room " + roomId + " cancelled (current booking).");
    }

    /**
     * Cancel a booking for the room made by a particular user (bookedBy).
     */
    public synchronized void cancelBooking(int roomId, String bookedBy) throws BookingException {
        List<Booking> roomBookings = bookings.get(roomId);
        if (roomBookings == null || roomBookings.isEmpty()) {
            throw new BookingException("Room " + roomId + " has no bookings. Cannot cancel.");
        }

        Booking toCancel = null;
        for (Booking b : roomBookings) {
            if (b.isActive() && Objects.equals(b.getBookedBy(), bookedBy)) {
                toCancel = b;
                break;
            }
        }

        if (toCancel == null) {
            throw new BookingException("No active booking found for user " + bookedBy + " in room " + roomId);
        }

        doCancelBooking(toCancel);
        logger.fine("Booking for Room " + roomId + " cancelled by user " + bookedBy);
    }

    /**
     * Internal cancellation routine (assumes caller has synchronized).
     */
    private void doCancelBooking(Booking booking) {
        booking.cancel();

        List<Booking> roomBookings = bookings.get(booking.getRoomId());
        if (roomBookings != null) {
            roomBookings.remove(booking);
            if (roomBookings.isEmpty()) bookings.remove(booking.getRoomId());
        }

        ScheduledFuture<?> f = autoReleaseTasks.remove(booking.hashCode());
        if (f != null) f.cancel(true);

        System.out.println("Cancelled " + booking);
    }

    /**
     * Return the active booking that is currently running for the room (if any).
     * This mirrors old Optional<Booking> getBooking(int roomId) semantics.
     */
    public synchronized Optional<Booking> getBooking(int roomId) {
        List<Booking> roomBookings = bookings.get(roomId);
        if (roomBookings == null) return Optional.empty();

        LocalDateTime now = LocalDateTime.now();
        for (Booking b : roomBookings) {
            if (b.isActive() && !now.isBefore(b.getStart()) && now.isBefore(b.getEnd())) {
                return Optional.of(b);
            }
        }
        return Optional.empty();
    }

    /**
     * Get all active bookings for a room (future & current).
     */
    public synchronized List<Booking> getBookings(int roomId) {
        List<Booking> roomBookings = bookings.get(roomId);
        if (roomBookings == null) return Collections.emptyList();

        List<Booking> active = new ArrayList<>();
        for (Booking b : roomBookings) {
            if (b.isActive()) active.add(b);
        }
        return Collections.unmodifiableList(active);
    }

    /**
     * Schedule auto-release for this booking (per-booking scheduling).
     */
    private void scheduleAutoRelease(Booking booking) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime runAt = booking.getStart().plusMinutes(autoReleaseWindowMinutes);
        long delayMillis = Duration.between(now, runAt).toMillis();
        if (delayMillis < 0) delayMillis = 0;

        Runnable task = () -> {
            try {
                Room room = config.getRoom(booking.getRoomId());
                if (room == null) return;
                if (!room.isOccupied() && booking.isActive()) {
                    // release this specific booking
                    releaseBooking(booking, "Unoccupied for > " + autoReleaseWindowMinutes + " minutes");
                } else {
                    logger.fine("Room " + booking.getRoomId() + " is occupied; auto-release skipped for booking " + booking);
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in auto-release task", e);
            }
        };

        ScheduledFuture<?> f = scheduler.schedule(task, delayMillis, TimeUnit.MILLISECONDS);
        autoReleaseTasks.put(booking.hashCode(), f);

        logger.fine("Scheduled auto-release for booking " + booking + " in ms=" + delayMillis);
    }

    /**
     * Release (cancel) the specific booking.
     */
    private synchronized void releaseBooking(Booking booking, String reason) {
        if (!booking.isActive()) return;

        booking.cancel();

        List<Booking> roomBookings = bookings.get(booking.getRoomId());
        if (roomBookings != null) {
            roomBookings.remove(booking);
            if (roomBookings.isEmpty()) bookings.remove(booking.getRoomId());
        }

        ScheduledFuture<?> f = autoReleaseTasks.remove(booking.hashCode());
        if (f != null) f.cancel(true);

        Room room = config.getRoom(booking.getRoomId());
        if (room != null && !room.isOccupied()) {
            room.setOccupancy(0);
        }

        System.out.println(String.format("Room %d booking released. Reason: %s", booking.getRoomId(), reason));
        logger.fine(String.format("Room %d booking released. Reason: %s", booking.getRoomId(), reason));
    }

    /**
     * Return a copy of all bookings (active & inactive). If you prefer only active,
     * change filtering logic below.
     */
    public synchronized Map<Integer, List<Booking>> getAllBookings() {
        Map<Integer, List<Booking>> copy = new HashMap<>();
        for (Map.Entry<Integer, List<Booking>> e : bookings.entrySet()) {
            copy.put(e.getKey(), new ArrayList<>(e.getValue()));
        }
        return Collections.unmodifiableMap(copy);
    }

    public void shutdown() {
        scheduler.shutdownNow();
        System.out.println("BookingManager shutting down...");
        logger.fine("BookingManager shutting down...");
    }
}

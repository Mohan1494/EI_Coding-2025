package com.smartoffice.model;

import java.time.LocalDateTime;

public class Booking {
    private final int roomId;
    private final LocalDateTime start;
    private final int durationMinutes;
    private final String bookedBy;
    private boolean active = true;

    public Booking(int roomId, LocalDateTime start, int durationMinutes, String bookedBy) {
        this.roomId = roomId;
        this.start = start;
        this.durationMinutes = durationMinutes;
        this.bookedBy = bookedBy;
    }

    public int getRoomId() { return roomId; }
    public LocalDateTime getStart() { return start; }
    public int getDurationMinutes() { return durationMinutes; }
    public LocalDateTime getEnd() { return start.plusMinutes(durationMinutes); }
    public String getBookedBy() { return bookedBy; }

    public synchronized boolean isActive() { return active; }
    public synchronized void cancel() { this.active = false; }

    @Override
    public String toString() {
        return String.format("Booking(room=%d start=%s dur=%d by=%s active=%b)", roomId, start.toString(), durationMinutes, bookedBy, active);
    }
}

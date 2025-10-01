package com.smartoffice.commands;

import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.Logger;

public class BookRoomCommand implements Command {

    private final BookingManager bm = BookingManager.getInstance();
    private final int roomId;
    private final LocalTime startTime;
    private final int minutes;
    private final String user;
    private final Logger logger = LoggerUtil.getLogger();

    public BookRoomCommand(int roomId, LocalTime startTime, int minutes, String user) {
        this.roomId = roomId;
        this.startTime = startTime;
        this.minutes = minutes;
        this.user = user;
    }

    @Override
    public void execute() {
        try {
            // Combine user-entered time with today's date
            LocalDateTime bookingStart = LocalDateTime.of(LocalDate.now(), startTime);

            // If the time has already passed today, assume booking starts now
            LocalDateTime now = LocalDateTime.now();
            if (bookingStart.isBefore(now)) {
                System.out.println("Since the scheduled time is earlier than the current time, the current time is taken.");
                bookingStart = now;
            }

            // Attempt booking
            bm.bookRoom(roomId, bookingStart, minutes, user);

            // Console message (clean)
            System.out.println("Room " + roomId + " booked from " + bookingStart.toLocalTime() +
                    " for " + minutes + " minutes.");


            logger.fine(String.format("Booking confirmed: Room %d | Start=%s | Duration=%d mins | User=%s",roomId, bookingStart, minutes, user));


        } catch (Exception e) {
            // Console error
            System.out.println("Booking failed: " + e.getMessage());

            // Log warning
            logger.warning("Booking failed for Room " + roomId + ": " + e.getMessage());
        }
    }
}

package com.smartoffice.commands;

import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class CancelBookingCommand implements Command {
    private final BookingManager bm = BookingManager.getInstance();
    private final int roomId;
    private final Logger logger = LoggerUtil.getLogger();

    public CancelBookingCommand(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public void execute() {
        try {
            bm.cancelBooking(roomId);
           System.out.println("Booking for Room " + roomId + " cancelled successfully."); 
           logger.fine("Booking for Room " + roomId + " cancelled successfully.");

        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }
}

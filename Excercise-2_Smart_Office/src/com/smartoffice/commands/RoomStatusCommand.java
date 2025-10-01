package com.smartoffice.commands;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Room;
import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class RoomStatusCommand implements Command {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final BookingManager bm = BookingManager.getInstance();
    private final int roomId;
    private final Logger logger = LoggerUtil.getLogger();

    public RoomStatusCommand(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public void execute() {
        Room room = config.getRoom(roomId);
        if (room == null) {
            String message = "Room " + roomId + " does not exist.";
            System.out.println(message); // Print to console
            logger.warning(message); // Log as warning
            return;
        }

        // Print and log room info
        String roomInfo = room.toString();
        System.out.println(roomInfo); // Print to console
        logger.fine(roomInfo); // Log at fine level

        // Print and log booking info if present
        bm.getBooking(roomId).ifPresent(b -> {
            String bookingInfo = "Booking: " + b.toString();
            System.out.println(bookingInfo); // Print to console
            logger.fine(bookingInfo); // Log at fine level
        });

    }
}

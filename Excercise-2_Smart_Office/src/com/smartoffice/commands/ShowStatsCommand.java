package com.smartoffice.commands;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Room;
import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class ShowStatsCommand implements Command {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final BookingManager bm = BookingManager.getInstance();
    private final Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute() {
       // Print and log room usage summary
    System.out.println("---- Room Usage Summary ----");
    logger.fine("---- Room Usage Summary ----");

    for (Room r : config.getAllRooms()) {
    // Print to console
    System.out.println(r.toString());
    // Log with fine level
    logger.fine(r.toString());
}

// Print and log active bookings count
System.out.println("Active bookings: " + bm.getAllBookings().size());
logger.fine("Active bookings: " + bm.getAllBookings().size());

    }
}

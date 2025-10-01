package com.smartoffice.commands;

import com.smartoffice.service.BookingManager;
import com.smartoffice.util.LoggerUtil;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class ExitCommand implements Command {
    private final AtomicBoolean running;
    private final BookingManager bm = BookingManager.getInstance();
    private final Logger logger = LoggerUtil.getLogger();

    public ExitCommand(AtomicBoolean running) {
        this.running = running;
    }

    @Override
    public void execute() {
        System.out.println("Shutting down application...");
        logger.fine("Shutting down application...");
        running.set(false);
        bm.shutdown();
    }
}

package com.smartoffice.util;

import java.io.IOException;
import java.util.logging.*;

public final class LoggerUtil {
    private static final Logger logger = Logger.getLogger("SmartOfficeLogger");
    private static FileHandler fileHandler;

    static {
        try {
           ConsoleHandler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.INFO);
            consoleHandler.setFormatter(new SimpleFormatter());
            logger.setUseParentHandlers(false);


            // File handler: capture all details
            FileHandler fileHandler = new FileHandler("smartoffice.log", true);
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(consoleHandler);
            logger.addHandler(fileHandler);

            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize log file: " + e.getMessage());
        }
    }

    private LoggerUtil() {}

    public static Logger getLogger() {
        return logger;
    }

    public static void setLevel(Level level) {
        logger.setLevel(level);
    }
}

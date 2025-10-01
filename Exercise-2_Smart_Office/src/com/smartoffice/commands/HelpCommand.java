package com.smartoffice.commands;

import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class HelpCommand implements Command {
    private final Logger logger = LoggerUtil.getLogger();

    @Override
    public void execute() {
       Logger logger = LoggerUtil.getLogger();

// List of commands
String[] commands = {
    "Config room count <n>",
    "Config room max capacity <roomId> <capacity>",
    "Block room <roomId> <HH:mm> <durationMinutes>",
    "Cancel room <roomId>",
    "Add occupant <roomId> <count>",
    "Room status <roomId>",
    "Stats",
    "Help",
    "Exit"
};

// Print and log each command
System.out.println("Available commands:");
for (String cmd : commands) {
    System.out.println("  " + cmd);
    logger.fine(cmd); // logs at FINE level
}

    }
}

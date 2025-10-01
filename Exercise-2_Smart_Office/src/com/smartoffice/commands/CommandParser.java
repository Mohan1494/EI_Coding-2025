package com.smartoffice.commands;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommandParser {
    private final AtomicBoolean running;

    public CommandParser(AtomicBoolean running) {
        this.running = running;
    }

    public Command parse(String line) {
        if (line == null) return null;
        String[] tokens = line.trim().split("\\s+");
        if (tokens.length == 0) return null;
        String cmd = tokens[0].toLowerCase();
        try {
            switch (cmd) {
                case "config":
                    if (tokens.length >= 4 && tokens[1].equalsIgnoreCase("room") && tokens[2].equalsIgnoreCase("count")) {
                        int n = Integer.parseInt(tokens[3]);
                        return new ConfigureRoomsCommand(n);
                    } else if (tokens.length >= 5 && tokens[1].equalsIgnoreCase("room") && tokens[2].equalsIgnoreCase("max") && tokens[3].equalsIgnoreCase("capacity")) {
                        int roomId = Integer.parseInt(tokens[4]);
                        int cap = Integer.parseInt(tokens[5]);
                        return new SetRoomCapacityCommand(roomId, cap);
                    }
                    break;
                case "block":
                    if (tokens.length >= 5 && tokens[1].equalsIgnoreCase("room")) {
                        int roomId = Integer.parseInt(tokens[2]);
                        java.time.LocalTime t = LocalTime.parse(tokens[3]);
                        int mins = Integer.parseInt(tokens[4]);
                        return new BookRoomCommand(roomId, t, mins, "system");
                    }
                    break;
                case "cancel":
                    if (tokens.length >= 3 && tokens[1].equalsIgnoreCase("room")) {
                        int roomId = Integer.parseInt(tokens[2]);
                        return new CancelBookingCommand(roomId);
                    }
                    break;
                case "add":
                    if (tokens.length >= 4 && tokens[1].equalsIgnoreCase("occupant")) {
                        int roomId = Integer.parseInt(tokens[2]);
                        int persons = Integer.parseInt(tokens[3]);
                        return new AddOccupantCommand(roomId, persons);
                    }
                    break;
                case "room":
                    if (tokens.length >= 3 && tokens[1].equalsIgnoreCase("status")) {
                        int roomId = Integer.parseInt(tokens[2]);
                        return new RoomStatusCommand(roomId);
                    }
                    break;
                case "stats":
                    return new ShowStatsCommand();
                case "help":
                    return new HelpCommand();
                case "logout":
                    return new LogoutCommand();
                case "exit":
                    return new ExitCommand(running);
            }
        } catch (Exception e) {
            // fallthrough and return null to indicate parse error
            return new HelpCommand();
        }
        return new HelpCommand();
    }
}

package com.smartoffice.app;

import com.smartoffice.commands.Command;
import com.smartoffice.commands.CommandParser;
import com.smartoffice.commands.LogoutCommand;
import com.smartoffice.config.OfficeConfig;
import com.smartoffice.auth.User;
import com.smartoffice.util.LoggerUtil;

import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = LoggerUtil.getLogger();
        logger.info("Starting Smart Office Application");

        System.out.println("\nAssumptions:");
        System.out.println("- Default room capacity = 10 (can be changed with 'Config room count' or 'Config room max capacity').");
        System.out.println("- Unoccupied bookings auto-release after 5 minutes.");
        System.out.println("- If scheduled time is earlier than current time, current time is taken.");
        System.out.println("- All bookings are tracked centrally by BookingManager.");
        System.out.println();

        AtomicBoolean running = new AtomicBoolean(true);
        OfficeConfig config = OfficeConfig.getInstance();
        Scanner scanner = new Scanner(System.in);

        User currentUser = null;

        // Main application loop
        while (running.get()) {

            // ----- LOGIN LOOP -----
            while (currentUser == null) {
                System.out.print("Username: ");
                String username = scanner.nextLine().trim();
                System.out.print("Password: ");
                String password = scanner.nextLine().trim();

                if ("admin".equals(username) && "admin123".equals(password)) {
                    currentUser = new User(username, User.Role.ADMIN);
                } else {
                    // Normal user
                    currentUser = new User(username, User.Role.USER);
                }

                System.out.println("Logged in as: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
                logger.info("User logged in: " + currentUser.getUsername() + " (" + currentUser.getRole() + ")");
            }

            // ----- COMMAND PARSER -----
            CommandParser parser = new CommandParser(running);

            System.out.println("\nType 'Help' for commands.\n");

            boolean logoutRequested = false;

            // ----- SESSION LOOP -----
            while (!logoutRequested && running.get()) {
                System.out.print("smart-office> ");
                String line = scanner.hasNextLine() ? scanner.nextLine().trim() : null;

                if (line == null || line.isEmpty()) {
                    continue;
                }

                Command c = parser.parse(line);
                if (c != null) {
                    try {
                        // Handle logout command
                        if (c instanceof LogoutCommand) {
                            logoutRequested = true;
                            currentUser = null;
                            System.out.println("Logged out successfully.\n");
                            break;
                        }

                        // Role-based access check
                        if (c.isConfigCommand() && currentUser.getRole() != User.Role.ADMIN) {
                            System.out.println("Permission denied: Only admin can execute config commands.");
                            continue;
                        }

                        // Execute the command
                        c.execute();

                    } catch (Exception e) {
                        logger.warning("Command execution failed: " + e.getMessage());
                    }
                }
            }
        }

        // ----- CLEANUP -----
        scanner.close();
        System.out.println("Application exited.");
        logger.fine("Application exited.");
    }
}

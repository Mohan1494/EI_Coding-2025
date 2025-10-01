package com.smartoffice.commands;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class SetRoomCapacityCommand implements Command {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final int roomId;
    private final int capacity;
    private final Logger logger = LoggerUtil.getLogger();

    public SetRoomCapacityCommand(int roomId, int capacity) {
        this.roomId = roomId;
        this.capacity = capacity;
    }

    @Override
    public void execute() {
        try {
            config.setRoomCapacity(roomId, capacity);
            System.out.println("Room " + roomId + " maximum capacity set to " + capacity + "."); 
            logger.fine("Room " + roomId + " maximum capacity set to " + capacity + ".");

        } catch (Exception e) {
            logger.warning("Failed to set capacity: " + e.getMessage());
        }
    }
    @Override
    public boolean isConfigCommand() {
        return true;
    }
}

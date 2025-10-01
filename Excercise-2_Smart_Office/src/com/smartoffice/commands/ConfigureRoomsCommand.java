package com.smartoffice.commands;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class ConfigureRoomsCommand implements Command {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final int count;
    private final Logger logger = LoggerUtil.getLogger();

    public ConfigureRoomsCommand(int count) {
        this.count = count;
    }

    @Override
    public void execute() {
        try {
            config.createRooms(count);
            System.out.println("Office configured with " + count + " meeting rooms.");
            config.getAllRooms().forEach(room -> 
            System.out.println("Room " + room.getId() + " - capacity=" + room.getMaxCapacity() 
                       + " occupancy=" + room.getOccupancy() 
                       + " occupied=" + room.isOccupied()));

        } catch (Exception e) {
            logger.warning("Failed to configure rooms: " + e.getMessage());
        }
    }
    
    @Override
    public boolean isConfigCommand() {
        return true;
    }

}

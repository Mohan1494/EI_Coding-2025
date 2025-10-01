package com.smartoffice.sensors;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Room;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class Sensor {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final Logger logger = LoggerUtil.getLogger();
    private final int roomId;

    public Sensor(int roomId) {
        this.roomId = roomId;
    }

    /**
     * Simulate detection: delta >0 add occupants, delta==0 means clear occupants
     */
    public void detect(int persons) {
        Room room = config.getRoom(roomId);
        if (room == null) {
            logger.warning("Sensor: Room " + roomId + " does not exist.");
            return;
        }
        try {
            if (persons <= 0) {
                room.setOccupancy(0);
                System.out.println("Sensor: Room " + roomId + " is now unoccupied."); 
                logger.fine("Sensor: Room " + roomId + " is now unoccupied.");

            } else {
                room.addOccupants(persons);
                System.out.println("Sensor: Detected entry of " + persons + " persons into the room " + roomId); 
                logger.fine("Sensor: Detected " + persons + " persons in room " + roomId);

            }
        } catch (Exception e) {
            logger.warning("Sensor error: " + e.getMessage());
        }
    }
}

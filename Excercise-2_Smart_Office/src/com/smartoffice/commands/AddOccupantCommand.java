package com.smartoffice.commands;

import com.smartoffice.config.OfficeConfig;
import com.smartoffice.model.Room;
import com.smartoffice.sensors.Sensor;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class AddOccupantCommand implements Command {
    private final OfficeConfig config = OfficeConfig.getInstance();
    private final int roomId;
    private final int persons;
    private final Logger logger = LoggerUtil.getLogger();

    public AddOccupantCommand(int roomId, int persons) {
        this.roomId = roomId;
        this.persons = persons;
    }

    @Override
    public void execute() {
        Room room = config.getRoom(roomId);
        if (room == null) {
            logger.warning("Room " + roomId + " does not exist.");
            return;
        }
        try {
            Sensor sensor = new Sensor(roomId);
            sensor.detect(persons);
            if (room.isOccupied() && persons >= 2) {
                System.out.println(String.format("Room %d is now occupied by %d persons. AC and lights turned on.", roomId, room.getOccupancy()));
                logger.fine(String.format("Occupancy change detected: Room %d, persons=%d (occupied).", roomId, room.getOccupancy()));
            } else if (!room.isOccupied()) {
                System.out.println(String.format("Room %d is now unoccupied. AC and lights turned off.", roomId));
                logger.fine(String.format("Occupancy change detected: Room %d became unoccupied.", roomId));
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }
}

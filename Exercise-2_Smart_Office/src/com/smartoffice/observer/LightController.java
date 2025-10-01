package com.smartoffice.observer;

import com.smartoffice.model.Room;
import com.smartoffice.util.LoggerUtil;

import java.util.logging.Logger;

public class LightController implements OccupancyObserver {
    private final Logger logger = LoggerUtil.getLogger();
    @Override
    public void onOccupancyChanged(Room room) {
        if (room.isOccupied()) {
            logger.info("Lights turned ON in " + room.getName());
        } else {
            logger.info("Lights turned OFF in " + room.getName());
        }
    }
}

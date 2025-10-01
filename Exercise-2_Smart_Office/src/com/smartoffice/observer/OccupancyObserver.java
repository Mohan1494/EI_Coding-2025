package com.smartoffice.observer;

import com.smartoffice.model.Room;

public interface OccupancyObserver {
    void onOccupancyChanged(Room room);
}

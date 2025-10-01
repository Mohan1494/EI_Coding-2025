package com.smartoffice.config;

import com.smartoffice.model.Room;
import java.util.*;
import java.util.logging.Logger;

public class OfficeConfig {
    private static OfficeConfig instance;
    private final Map<Integer, Room> rooms = new HashMap<>();
    private final Logger logger = Logger.getLogger(OfficeConfig.class.getName());

    private OfficeConfig() {}

    public static synchronized OfficeConfig getInstance() {
        if (instance == null) instance = new OfficeConfig();
        return instance;
    }

    public synchronized void createRooms(int count) {
        if (count <= 0) throw new IllegalArgumentException("Room count must be positive");
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            rooms.put(i, new Room(i, "Room " + i));
        }
        logger.fine("Configured " + count + " rooms");  // goes only to file, not console
    }

    public synchronized Room getRoom(int id) {
        return rooms.get(id);
    }

    public synchronized Collection<Room> getAllRooms() {
        return Collections.unmodifiableCollection(rooms.values());
    }

    public synchronized void setRoomCapacity(int roomId, int capacity) {
        Room r = rooms.get(roomId);
        if (r == null) throw new IllegalArgumentException("Invalid room id");
        r.setMaxCapacity(capacity);
        logger.fine("Room " + roomId + " maximum capacity set to " + capacity);
    }
}

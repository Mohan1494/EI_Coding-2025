package com.smartoffice.model;

import com.smartoffice.observer.OccupancyObserver;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Room {
    private final int id;
    private String name;
    private int maxCapacity = 10;
    private int occupancy = 0;
    private volatile boolean occupied = false;
    private final List<OccupancyObserver> observers = new CopyOnWriteArrayList<>();

    public Room(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public synchronized int getMaxCapacity() { return maxCapacity; }
    public synchronized void setMaxCapacity(int maxCapacity) {
        if (maxCapacity <= 0) throw new IllegalArgumentException("Capacity must be positive");
        this.maxCapacity = maxCapacity;
    }

    public synchronized int getOccupancy() { return occupancy; }

    public synchronized void addOccupants(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta must be >= 0");
        if (this.occupancy + delta > maxCapacity) throw new IllegalArgumentException("Exceeds room capacity");
        this.occupancy += delta;
        evaluateOccupancy();
        notifyObservers();
    }

    public synchronized void removeOccupants(int delta) {
        if (delta < 0) throw new IllegalArgumentException("Delta must be >= 0");
        this.occupancy = Math.max(0, this.occupancy - delta);
        evaluateOccupancy();
        notifyObservers();
    }

    public synchronized void setOccupancy(int value) {
        if (value < 0) throw new IllegalArgumentException("Occupancy cannot be negative");
        this.occupancy = Math.min(value, maxCapacity);
        evaluateOccupancy();
        notifyObservers();
    }

    private void evaluateOccupancy() {
        boolean newOccupied = this.occupancy >= 2;
        this.occupied = newOccupied;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void addObserver(OccupancyObserver o) { observers.add(o); }
    public void removeObserver(OccupancyObserver o) { observers.remove(o); }

    private void notifyObservers() {
        for (OccupancyObserver o : observers) {
            try {
                o.onOccupancyChanged(this);
            } catch (Exception e) {
                // observer failure should not break system
                com.smartoffice.util.LoggerUtil.getLogger().warning("Observer threw: " + e.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Room %d (%s) - capacity=%d occupancy=%d occupied=%b", id, name, maxCapacity, occupancy, occupied);
    }
}

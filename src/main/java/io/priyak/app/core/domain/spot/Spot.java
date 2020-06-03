package io.priyak.app.core.domain.spot;

import io.priyak.app.core.domain.vehicle.Vehicle;

/**
 * Abstract class to represent a generic parking spot
 *
 * Any specific implementation of @{@link ParkingType} needs to extend this class
 *
 * @author priyakdey
 */
public abstract class Spot {
    private final int spotNumber;
    private final ParkingType type;
    private boolean occupied;           // default: false
    private Vehicle parkedVehicle;

    Spot(int spotNumber, ParkingType type) {
        this.spotNumber = spotNumber;
        this.type = type;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public void setParkedVehicle(Vehicle parkedVehicle) {
        this.parkedVehicle = parkedVehicle;
    }

    public ParkingType getType() {
        return type;
    }
}

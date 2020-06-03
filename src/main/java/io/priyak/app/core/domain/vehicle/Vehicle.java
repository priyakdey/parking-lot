package io.priyak.app.core.domain.vehicle;

/**
 * Abstract class to represent any vehicle
 *
 * Any specific implementation of @{@link VehicleType} needs to extend this class
 *
 * @author priyakdey
 */
public abstract class Vehicle {
    private final String registrationNumber;
    private final VehicleType type;

    Vehicle(String registrationNumber, VehicleType type) {
        this.registrationNumber = registrationNumber;
        this.type = type;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public VehicleType getType() {
        return type;
    }
}
package io.priyak.app.core.domain.vehicle;

import static io.priyak.app.core.domain.vehicle.VehicleType.GENERAL;

public class GeneralVehicle extends Vehicle {
    public GeneralVehicle(String registrationNumber) {
        super(registrationNumber, GENERAL);
    }
}

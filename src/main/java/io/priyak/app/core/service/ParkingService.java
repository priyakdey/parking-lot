package io.priyak.app.core.service;

import io.priyak.app.core.domain.spot.Spot;

import java.util.List;

/**
 * Interface to define the contract of any @{@link ParkingService} type
 *
 * @author priyakdey
 */
public interface ParkingService {

    /**
     * Method to automatically allocate a @{@link Spot} to the incoming @{@link io.priyak.app.core.domain.vehicle.Vehicle}
     * (@param registrationNumber)
     * @return java.lang.String
     */
    String park(String registrationNumber);

    /**
     * Method to automate the process of billing when a @{@link io.priyak.app.core.domain.vehicle.Vehicle}
     * exists the @{@link io.priyak.app.core.domain.ParkingLot}
     * (@param registrationNumber)
     * (@param hoursParked)
     * @return java.lang.String
     */
    String leave(String registrationNumber, int hoursParked);

    /**
     * Method to return a snapshot of the @{@link io.priyak.app.core.domain.ParkingLot} at current moment
     * @return List<? extends Spot>
     */
    List<? extends Spot> status();

}

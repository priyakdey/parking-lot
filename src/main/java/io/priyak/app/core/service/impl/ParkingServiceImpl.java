package io.priyak.app.core.service.impl;

import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.domain.ParkingLot;
import io.priyak.app.core.domain.spot.Spot;
import io.priyak.app.core.domain.vehicle.GeneralVehicle;
import io.priyak.app.core.domain.vehicle.Vehicle;
import io.priyak.app.core.exception.NoParkingAvailableException;
import io.priyak.app.core.service.ParkingService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Simple impl for @{@link ParkingService}
 * This class is stateless and hence can be created as a @{@link Singleton}
 *
 * @author priyakdey
 */
@Singleton
public class ParkingServiceImpl implements ParkingService {
    private final ParkingLot parkingLot;
    private final AvailableSpotStrategy availableSpotStrategy;

    @Inject
    public ParkingServiceImpl(ParkingLot parkingLot, AvailableSpotStrategy availableSpotStrategy) {
        this.parkingLot = parkingLot;
        this.availableSpotStrategy = availableSpotStrategy;
    }

    @Override
    public String park(String registrationNumber) {
        if (parkingLot.isFull()) {
            throw new NoParkingAvailableException("Sorry, parking lot is full");
        }

        // Get the nearest empty spot
        final List<? extends Spot> spots = parkingLot.getSpots();
        int availableSpot = this.availableSpotStrategy.availableSpot(spots);

        // Allocated the available spot the the vehicle
        final Vehicle vehicle = new GeneralVehicle(registrationNumber);
        final Spot spot = spots.get(availableSpot - 1);
        spot.setParkedVehicle(vehicle);
        spot.setOccupied(true);

        // Set flags
        parkingLot.setEmpty(isParkingLotEmpty());
        parkingLot.setFull(isParkingLotFull());

        return "Allocated slot number: " + availableSpot;
    }

    private boolean isParkingLotFull() {
        return parkingLot.getSpots()
                .stream()
                .allMatch(Spot::isOccupied);
    }

    private boolean isParkingLotEmpty() {
        return parkingLot.getSpots()
                    .stream()
                    .noneMatch(Spot::isOccupied);
    }

    @Override
    public String leave(String registrationNumber, int hoursParked) {
        return null;
    }

    @Override
    public List<? extends Spot> status() {
        return null;
    }

}

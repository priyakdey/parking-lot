package io.priyak.app.core.service.impl;

import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.common.strategy.PricingStrategy;
import io.priyak.app.core.domain.ParkingLot;
import io.priyak.app.core.domain.spot.Spot;
import io.priyak.app.core.domain.vehicle.GeneralVehicle;
import io.priyak.app.core.domain.vehicle.Vehicle;
import io.priyak.app.core.exception.NoParkingAvailableException;
import io.priyak.app.core.exception.NoVehicleFoundException;
import io.priyak.app.core.service.ParkingService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Simple impl for @{@link ParkingService}
 * This class is stateless and hence can be created as a @{@link Singleton}
 *
 * @author priyakdey
 */
@SuppressWarnings("UnnecessaryLocalVariable")
@Singleton
public class ParkingServiceImpl implements ParkingService {
    private final ParkingLot parkingLot;
    private final AvailableSpotStrategy availableSpotStrategy;
    private final PricingStrategy pricingStrategy;

    @Inject
    public ParkingServiceImpl(ParkingLot parkingLot,
                              AvailableSpotStrategy availableSpotStrategy,
                              PricingStrategy pricingStrategy) {
        this.parkingLot = parkingLot;
        this.availableSpotStrategy = availableSpotStrategy;
        this.pricingStrategy = pricingStrategy;
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
        final String errorMessage = String.format("Registration number %s not found", registrationNumber);

        // Find the parked spot or else throw exception
        Spot parkedSpot = parkingLot.getSpots()
                                     .stream()
                                     .filter(Spot::isOccupied)
                                     .filter(spot -> Objects.equals(spot.getParkedVehicle().getRegistrationNumber(),
                                                                    registrationNumber))
                                     .findFirst()
                                     .orElseThrow(() -> new NoVehicleFoundException(errorMessage));

        // Set occupied and vehicle to default value
        parkedSpot.setOccupied(false);
        parkedSpot.setParkedVehicle(null);

        // Set Flags
        parkingLot.setEmpty(isParkingLotEmpty());
        parkingLot.setFull(isParkingLotFull());

        // Find parking charges
        Integer charges = (Integer) pricingStrategy.charges(hoursParked);

        final String returnMessage = String.format("Registration number %s with Slot Number %s is free with Charge %s",
                registrationNumber, parkedSpot.getSpotNumber(), charges);
        return returnMessage;
    }

    @Override
    public List<? extends Spot> status() {
        if (parkingLot.isEmpty()) {
            return new ArrayList<>();
        }

        return parkingLot.getSpots()
                .stream()
                .filter(Spot::isOccupied).collect(Collectors.toList());
    }

}

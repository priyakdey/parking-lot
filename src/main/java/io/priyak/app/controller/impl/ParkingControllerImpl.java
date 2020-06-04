package io.priyak.app.controller.impl;

import io.priyak.app.controller.ParkingController;
import io.priyak.app.core.exception.NoParkingAvailableException;
import io.priyak.app.core.exception.NoVehicleFoundException;
import io.priyak.app.core.service.ParkingService;
import io.priyak.app.operation.OperationSet;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

import static io.priyak.app.operation.Operation.LEAVE;
import static io.priyak.app.operation.Operation.PARK;

@Singleton
public class ParkingControllerImpl implements ParkingController {
    private final ParkingService parkingService;
    private OperationSet operationSet;

    @Inject
    public ParkingControllerImpl(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @Override
    public void setOperationSet(OperationSet operationSet) {
        this.operationSet = operationSet;
    }

    @Override
    public void process() {
        String[] arr;
        while ((arr = operationSet.take()) != null) {
            if (Objects.equals(arr[0], PARK.getOperation())) {
                this.park(arr[1]);
            } else if (Objects.equals(arr[0], LEAVE.getOperation())) {
                this.leave(arr[1], Integer.valueOf(arr[2]));
            } else {
                parkingService.status();
            }
        }
    }

    private void leave(String registrationNumber, Integer hoursParked) {
        try {
            final String response = parkingService.leave(registrationNumber, hoursParked);
            System.out.println(response);
        } catch (NoVehicleFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void park(String registrationNumber) {
        try {
            final String response = parkingService.park(registrationNumber);
            System.out.println(response);
        } catch (NoParkingAvailableException e) {
            System.out.println(e.getMessage());
        }
    }
}

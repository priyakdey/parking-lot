package io.priyak.app.core.domain;

import io.priyak.app.core.common.factory.GeneralParkingSpotFactory;
import io.priyak.app.core.common.factory.ParkingSpotFactory;
import io.priyak.app.core.domain.spot.Spot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parking Lot Test")
class ParkingLotTest {
    ParkingLot parkingLot;
    ParkingSpotFactory factory;

    @BeforeEach
    void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        final Constructor<ParkingLot> constructor =
                ParkingLot.class.getDeclaredConstructor(ParkingSpotFactory.class, int.class);
        constructor.setAccessible(true);

        final int numberOfSpots = 3;
        this.factory = new GeneralParkingSpotFactory();

        this.parkingLot = constructor.newInstance(factory, numberOfSpots);
    }

    @Test
    @DisplayName("should return initialised parking lot")
    void getSpots() {
        final List<? extends Spot> spots = parkingLot.getSpots();

        assertAll(
                () -> assertNotNull(parkingLot),
                () -> assertEquals(3, spots.size()),
                () -> assertEquals(1, spots.get(0).getSpotNumber()),
                () -> assertFalse(spots.get(0).isOccupied()),
                () -> assertNull(spots.get(0).getParkedVehicle()),
                () -> assertEquals(2, spots.get(1).getSpotNumber()),
                () -> assertFalse(spots.get(1).isOccupied()),
                () -> assertNull(spots.get(1).getParkedVehicle()),
                () -> assertEquals(3, spots.get(2).getSpotNumber()),
                () -> assertFalse(spots.get(2).isOccupied()),
                () -> assertNull(spots.get(2).getParkedVehicle())
        );
    }
}
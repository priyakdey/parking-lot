package io.priyak.app.core.domain.common.factory;

import io.priyak.app.core.domain.spot.Spot;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("General Type Parking Spots Factory Test")
class GeneralParkingSpotFactoryTest {

    @Test
    @DisplayName("should return a list of General Spots")
    void create() {
        final GeneralParkingSpotFactory factory = new GeneralParkingSpotFactory();
        final List<? extends Spot> actual = factory.create(3);

        assertAll(
                () -> assertNotNull(actual),
                () -> assertEquals(3, actual.size()),
                () -> assertEquals(1, actual.get(0).getSpotNumber()),
                () -> assertFalse(actual.get(0).isOccupied()),
                () -> assertNull(actual.get(0).getParkedVehicle()),
                () -> assertEquals(2, actual.get(1).getSpotNumber()),
                () -> assertFalse(actual.get(1).isOccupied()),
                () -> assertNull(actual.get(1).getParkedVehicle()),
                () -> assertEquals(3, actual.get(2).getSpotNumber()),
                () -> assertFalse(actual.get(2).isOccupied()),
                () -> assertNull(actual.get(2).getParkedVehicle())
        );
    }
}
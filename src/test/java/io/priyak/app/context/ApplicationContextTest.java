package io.priyak.app.context;

import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.domain.ParkingLot;
import io.priyak.app.core.service.ParkingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Application Context Test")
class ApplicationContextTest {
    ApplicationContext context;

    @BeforeEach
    void setUp() {
        context = new ApplicationContext();
    }

    @Test
    @DisplayName("should wire all components")
    // Currently testing if it wires the Parking Lot correctly
    void getController() throws NoSuchFieldException, IllegalAccessException {
        final ParkingService service = context.getController(3);

        Field parkingLotField = service.getClass().getDeclaredField("parkingLot");
        parkingLotField.setAccessible(true);
        ParkingLot parkingLot = (ParkingLot) parkingLotField.get(service);

        Field availableSpotStrategyField = service.getClass().getDeclaredField("availableSpotStrategy");
        availableSpotStrategyField.setAccessible(true);
        AvailableSpotStrategy availableSpotStrategy = (AvailableSpotStrategy) availableSpotStrategyField.get(service);

        assertAll(
                () -> assertNotNull(service),
                () -> assertNotNull(parkingLot),
                () -> assertNotNull(availableSpotStrategy),
                () -> assertEquals(3, parkingLot.getSpots().size())
        );
    }
}
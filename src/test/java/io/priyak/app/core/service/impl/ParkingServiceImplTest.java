package io.priyak.app.core.service.impl;

import io.priyak.app.core.common.factory.GeneralParkingSpotFactory;
import io.priyak.app.core.common.factory.ParkingSpotFactory;
import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.common.strategy.PricingStrategy;
import io.priyak.app.core.common.strategy.impl.NearestAvailableSpotStrategyImpl;
import io.priyak.app.core.common.strategy.impl.PricingStrategyImpl;
import io.priyak.app.core.domain.ParkingLot;
import io.priyak.app.core.domain.spot.Spot;
import io.priyak.app.core.domain.vehicle.GeneralVehicle;
import io.priyak.app.core.exception.NoParkingAvailableException;
import io.priyak.app.core.exception.NoVehicleFoundException;
import io.priyak.app.core.service.ParkingService;
import org.junit.jupiter.api.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Parking Service Impl Test")
class ParkingServiceImplTest {
    @Nested
    @DisplayName("Park() Method Test Suite")
    class Park {
        ParkingService parkingService;
        ParkingLot parkingLot;

        @BeforeEach
        void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            final ParkingSpotFactory factory = new GeneralParkingSpotFactory();
            final int numberOfSpots = 6;
            final Constructor<ParkingLot> constructor =
                    ParkingLot.class.getDeclaredConstructor(ParkingSpotFactory.class, int.class);
            constructor.setAccessible(true);
            this.parkingLot = constructor.newInstance(factory, numberOfSpots);

            final AvailableSpotStrategy emptySpotStrategy = new NearestAvailableSpotStrategyImpl();
            final PricingStrategy pricingStrategy = new PricingStrategyImpl();
            this.parkingService = new ParkingServiceImpl(parkingLot, emptySpotStrategy, pricingStrategy);
        }

        @Test
        @DisplayName("should allocate Spot 1")
        void parkScenario1() {
            final String actual = parkingService.park("MH-12-FB-6636");
            final String expected = "Allocated slot number: 1";

            assertAll(
                    () -> assertEquals(expected, actual, "Allocated spot should be 1"),
                    () -> assertFalse(parkingLot.isEmpty(), "Should not be empty"),
                    () -> assertFalse(parkingLot.isFull(), "Should not be full")
            );
        }

        @Test
        @DisplayName("should allocate Spot 3")
        void parkScenario2() {
            parkingLot.getSpots().get(0).setParkedVehicle(new GeneralVehicle("MH-02-AZ-6636")); // 1
            parkingLot.getSpots().get(0).setOccupied(true);

            parkingLot.getSpots().get(1).setParkedVehicle(new GeneralVehicle("MH-12-FB-6754")); // 2
            parkingLot.getSpots().get(1).setOccupied(true);

            parkingLot.getSpots().get(5).setParkedVehicle(new GeneralVehicle("MH-14-FB-1234")); // 6
            parkingLot.getSpots().get(5).setOccupied(true);

            final String actual = parkingService.park("MH-12-FB-6636");
            final String expected = "Allocated slot number: 3";

            assertAll(
                    () -> assertEquals(expected, actual, "Allocated Spot should be 3"),
                    () -> assertFalse(parkingLot.isEmpty(), "Should not be empty"),
                    () -> assertFalse(parkingLot.isFull(), "Should not be full")
            );
        }

        @Test
        @DisplayName("should throw No Parking Available Exception")
        void parkScenario3() {
            parkingLot.getSpots().get(0).setParkedVehicle(new GeneralVehicle("MH-02-AZ-6636"));
            parkingLot.getSpots().get(1).setParkedVehicle(new GeneralVehicle("MH-12-FB-6754"));
            parkingLot.getSpots().get(5).setParkedVehicle(new GeneralVehicle("MH-14-FB-1234"));

            parkingService.park("MH-12-FB-6636");
            parkingService.park("MH-02-AZ-6636");
            parkingService.park("MH-12-FB-6754");
            parkingService.park("MH-14-FB-1234");
            parkingService.park("WB-06-FB-1234");
            parkingService.park("KA-01-AZ-6636");


            assertAll(
                    () -> assertThrows(NoParkingAvailableException.class,
                            () -> parkingService.park("WB-02-FB-9876"), "Throws an Exception"),
                    () -> assertFalse(parkingLot.isEmpty(), "Should not be empty"),
                    () -> assertTrue(parkingLot.isFull(), "Should be Full")
            );
        }
    }

    @Nested
    @DisplayName("Leave() Method Test Suite")
    class Leave {
        ParkingService parkingService;
        ParkingLot parkingLot;

        @BeforeEach
        void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            final ParkingSpotFactory factory = new GeneralParkingSpotFactory();
            final int numberOfSpots = 3;
            final Constructor<ParkingLot> constructor =
                    ParkingLot.class.getDeclaredConstructor(ParkingSpotFactory.class, int.class);
            constructor.setAccessible(true);
            this.parkingLot = constructor.newInstance(factory, numberOfSpots);

            final AvailableSpotStrategy emptySpotStrategy = new NearestAvailableSpotStrategyImpl();
            final PricingStrategy pricingStrategy = new PricingStrategyImpl();
            this.parkingService = new ParkingServiceImpl(parkingLot, emptySpotStrategy, pricingStrategy);
        }

        @Test
        @DisplayName("should free up spot 1")
        void leaveScenario1() {
            parkingService.park("MH-12-FB-6636");
            parkingService.park("MH-02-AZ-6636");
            parkingService.park("MH-12-FB-6754");

            final String actual = parkingService.leave("MH-12-FB-6636", 2);
            final String expected = "Registration number MH-12-FB-6636 with Slot Number 1 is free with Charge 10";

            assertAll(
                    () -> assertEquals(expected, actual),
                    () -> assertFalse(parkingLot.getSpots().get(0).isOccupied()),
                    () -> assertFalse(parkingLot.isEmpty()),
                    () -> assertFalse(parkingLot.isFull())
            );
        }

        @Test
        @DisplayName("should throw NoVehicleFoundException")
        void leaveScenario2() {
            parkingService.park("MH-12-FB-6636");
            parkingService.park("MH-02-AZ-6636");
            parkingService.park("MH-12-FB-6754");

            assertAll(
                    () -> assertThrows(NoVehicleFoundException.class,
                            () -> parkingService.leave("MH-12-FB-0000", 2)),
                    () -> assertFalse(parkingLot.isEmpty()),
                    () -> assertTrue(parkingLot.isFull())
            );
        }

        @Test
        @DisplayName("should be empty now")
        void leaveScenario3() {
            parkingService.park("MH-12-FB-6636");
            parkingService.park("MH-02-AZ-6636");
            parkingService.park("MH-12-FB-6754");

            parkingService.leave("MH-12-FB-6636", 2);
            parkingService.leave("MH-02-AZ-6636", 2);
            parkingService.leave("MH-12-FB-6754", 2);

            assertAll(
                    () -> assertTrue(parkingLot.isEmpty()),
                    () -> assertFalse(parkingLot.isFull())
            );
        }
    }

    @Nested
    @DisplayName("status() method test suite")
    class Status {
        ParkingService parkingService;
        ParkingLot parkingLot;

        @BeforeEach
        void setUp() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
            final ParkingSpotFactory factory = new GeneralParkingSpotFactory();
            final int numberOfSpots = 3;
            final Constructor<ParkingLot> constructor =
                    ParkingLot.class.getDeclaredConstructor(ParkingSpotFactory.class, int.class);
            constructor.setAccessible(true);
            this.parkingLot = constructor.newInstance(factory, numberOfSpots);

            final AvailableSpotStrategy emptySpotStrategy = new NearestAvailableSpotStrategyImpl();
            final PricingStrategy pricingStrategy = new PricingStrategyImpl();
            this.parkingService = new ParkingServiceImpl(parkingLot, emptySpotStrategy, pricingStrategy);
        }

        @Test
        @DisplayName("should return a new object")
        void statusScenario1() {
            final List<? extends Spot> clone = parkingService.status();
            final List<? extends Spot> original = parkingLot.getSpots();

            assertNotEquals(original.hashCode(), clone.hashCode());
        }

        @Test
        @DisplayName("should return current state of the parking lot")
        void statusScenario2() {
            final List<? extends Spot> actual = parkingService.status();
            final List<? extends Spot> expected = parkingLot.getSpots();

            // TODO: To be done
        }
    }
}
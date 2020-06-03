package io.priyak.app.core.common.strategy.impl;

import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.domain.spot.GeneralSpot;
import io.priyak.app.core.domain.spot.Spot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NearestAvailableSpotStrategyImplTest {
    AvailableSpotStrategy availableSpotStrategy;
    List<? extends Spot> spots;

    @BeforeEach
    void setUp() {
        this.availableSpotStrategy = new NearestAvailableSpotStrategyImpl();
        this.spots = Arrays.asList(new GeneralSpot(1),
                new GeneralSpot(2),
                new GeneralSpot(3),
                new GeneralSpot(4),
                new GeneralSpot(5));
    }

    @Test
    @DisplayName("should return 1")
    void availableSpotScenario1()  {
        final int actual = availableSpotStrategy.availableSpot(spots);
        final int expected = 1;

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("should return 3")
    void availableSpotScenario2()  {
        spots.get(0).setOccupied(true);
        spots.get(1).setOccupied(true);
        spots.get(3).setOccupied(true);

        final int actual = availableSpotStrategy.availableSpot(spots);
        final int expected = 3;

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 2")
    void availableSpotScenario3() {
        spots.get(0).setOccupied(true);
        spots.get(2).setOccupied(true);
        spots.get(4).setOccupied(true);

        final int actual = availableSpotStrategy.availableSpot(spots);
        final int expected = 2;

        assertEquals(expected, actual);
    }
}
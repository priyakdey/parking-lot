package io.priyak.app.core.common.strategy.impl;

import io.priyak.app.core.common.strategy.PricingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Pricing Strategy Impl Test")
class PricingStrategyImplTest {
    PricingStrategy pricingStrategy = new PricingStrategyImpl();

    @Test
    @DisplayName("should return 10")
    void chargesScenario1() {
        final Number actual = pricingStrategy.charges(1);
        final Number expected = Integer.valueOf(10);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 10")
    void chargesScenario2() {
        final Number actual = pricingStrategy.charges(2);
        final Number expected = Integer.valueOf(10);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should return 50")
    void chargesScenario3() {
        final Number actual = pricingStrategy.charges(6);
        final Number expected = Integer.valueOf(50);

        assertEquals(expected, actual);
    }
}
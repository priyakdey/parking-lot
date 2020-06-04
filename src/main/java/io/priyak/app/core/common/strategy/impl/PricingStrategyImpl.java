package io.priyak.app.core.common.strategy.impl;

import io.priyak.app.core.common.strategy.PricingStrategy;

import javax.inject.Singleton;

@Singleton
public class PricingStrategyImpl implements PricingStrategy {

    @Override
    public Integer charges(Number numberOfHours) {
        final int baseCharge = 10;
        final int perHourCharge = 10;

        return (int) numberOfHours > 2 ?
                ((int )numberOfHours - 2) * perHourCharge + baseCharge :
                baseCharge;
    }
}

package io.priyak.app.core.common.strategy.impl;

import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.domain.spot.Spot;

import javax.inject.Singleton;
import java.util.List;
import java.util.Objects;

@Singleton
public class NearestAvailableSpotStrategyImpl implements AvailableSpotStrategy {

    @SuppressWarnings("OptionalGetWithoutIsPresent")
    @Override
    public int availableSpot(List<? extends Spot> spots) {
        return spots.stream()
                        .filter(spot -> Objects.equals(spot.isOccupied(), false))
                        .mapToInt(Spot::getSpotNumber)
                        .min()
                        .getAsInt();
    }

}

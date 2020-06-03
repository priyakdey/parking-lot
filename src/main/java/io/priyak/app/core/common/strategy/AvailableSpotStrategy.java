package io.priyak.app.core.common.strategy;

import io.priyak.app.core.domain.spot.Spot;

import java.util.List;

/**
 * Interface to be extended by various concrete strategies to find an available {@link Spot}
 */
public interface AvailableSpotStrategy {

    int availableSpot(List<? extends Spot> spots);

}

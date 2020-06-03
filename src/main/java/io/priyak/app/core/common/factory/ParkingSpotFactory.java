package io.priyak.app.core.common.factory;

import io.priyak.app.core.domain.spot.Spot;

import java.util.List;

/**
 * Interface to abstract the creation of @{@link io.priyak.app.core.domain.spot.Spot}
 * of different @{@link io.priyak.app.core.domain.spot.ParkingType}
 *
 * Any new concrete factory implementation should implement this interface
 *
 * @author priyakdey
 */
public interface ParkingSpotFactory {

    List<? extends Spot> create(int numberOfSpots);

}

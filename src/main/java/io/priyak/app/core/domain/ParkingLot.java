package io.priyak.app.core.domain;

import com.google.inject.Singleton;
import io.priyak.app.core.domain.common.factory.ParkingSpotFactory;
import io.priyak.app.core.domain.spot.Spot;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to act as a facade for the individual @{@link Spot}
 *
 * @author priyakdey
 */
@Singleton
public final class ParkingLot {
    private final List<? extends Spot> spots;

    /** Private Constructor since DI Framework is to initialise the parking lot as singleton **/
    @Inject
    private ParkingLot(ParkingSpotFactory factory, @Count int numberOfSpots) {
        this.spots = factory.create(numberOfSpots);
    }

    public List<? extends Spot> getSpots() {
        return new ArrayList<>(spots);
    }
}

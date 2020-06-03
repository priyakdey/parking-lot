package io.priyak.app.core.domain;


import io.priyak.app.core.common.factory.ParkingSpotFactory;
import io.priyak.app.core.domain.spot.Spot;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

/**
 * Class to act as a facade for the individual @{@link Spot}
 *
 * @author priyakdey
 */
@Singleton
public final class ParkingLot {
    private final List<? extends Spot> spots;
    private boolean empty;
    private boolean full;

    /** Private Constructor since DI Framework is to initialise the parking lot as singleton **/
    @Inject
    private ParkingLot(ParkingSpotFactory factory, @Count int numberOfSpots) {
        this.spots = factory.create(numberOfSpots);
        this.empty = true;
        this.full = false;
    }

    public List<? extends Spot> getSpots() {
        return this.spots;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public boolean isFull() {
        return full;
    }

    public void setFull(boolean full) {
        this.full = full;
    }
}

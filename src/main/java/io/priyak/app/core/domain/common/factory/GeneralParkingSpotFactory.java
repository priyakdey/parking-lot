package io.priyak.app.core.domain.common.factory;

import io.priyak.app.core.domain.spot.GeneralSpot;
import io.priyak.app.core.domain.spot.Spot;

import java.util.ArrayList;
import java.util.List;

public class GeneralParkingSpotFactory implements ParkingSpotFactory {

    @Override
    public List<? extends Spot> create(int numberOfSpots) {
        List<GeneralSpot> spots = new ArrayList<>(numberOfSpots);
        for (int i = 1; i <= numberOfSpots; i++) {
            spots.add(new GeneralSpot(i));
        }

        return spots;
    }
}

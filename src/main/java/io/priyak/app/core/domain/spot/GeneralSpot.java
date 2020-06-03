package io.priyak.app.core.domain.spot;

import static io.priyak.app.core.domain.spot.ParkingType.GENERAL;

public class GeneralSpot extends Spot {
    public GeneralSpot(int spotNumber) {
        super(spotNumber, GENERAL);
    }
}

package io.priyak.app.context.config;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.priyak.app.context.Configuration;
import io.priyak.app.core.domain.Count;
import io.priyak.app.core.common.factory.GeneralParkingSpotFactory;
import io.priyak.app.core.common.factory.ParkingSpotFactory;

@SuppressWarnings("unused")
@Configuration
public class ParkingLotModule extends AbstractModule {
    private final int numberOfSpots;

    public ParkingLotModule(int numberOfSpots) {
        this.numberOfSpots = numberOfSpots;
    }

    @Override
    protected void configure() {
        bind(ParkingSpotFactory.class).to(GeneralParkingSpotFactory.class);
    }

    @Provides
    @Count
    int getNumberOfSpots() {
        return numberOfSpots;
    }
}

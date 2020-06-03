package io.priyak.app.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.priyak.app.context.config.ParkingLotModule;
import io.priyak.app.core.domain.ParkingLot;

/**
 * Context to wire up all components of the application
 *
 * @author priyakdey
 */
public class ApplicationContext {

    // TODO: Method def and implementation to be changed.
    public ParkingLot getController(int numberOfSpots) {
        final Injector injector = Guice.createInjector(new ParkingLotModule(numberOfSpots));
        final ParkingLot parkingLot = injector.getInstance(ParkingLot.class);
        return parkingLot;
    }

}

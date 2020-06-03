package io.priyak.app.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.priyak.app.context.config.AvailableSpotStrategyModule;
import io.priyak.app.context.config.ParkingLotModule;
import io.priyak.app.context.config.ParkingServiceModule;
import io.priyak.app.context.config.PricingStrategyModule;
import io.priyak.app.core.service.ParkingService;

import java.util.List;

/**
 * Context to wire up all components of the application
 *
 * @author priyakdey
 */
public class ApplicationContext {

    // TODO: Method def and implementation to be changed.
    public ParkingService getController(int numberOfSpots) {
        final List<Module> configurationModules = List.of(new ParkingServiceModule(),
                                                          new AvailableSpotStrategyModule(),
                                                          new PricingStrategyModule(),
                                                          new ParkingLotModule(numberOfSpots));
        final Injector injector = Guice.createInjector(configurationModules);
        final ParkingService parkingService = injector.getInstance(ParkingService.class);
        return parkingService;
    }

}

package io.priyak.app.context;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.priyak.app.context.config.*;
import io.priyak.app.controller.ParkingController;

import java.util.List;

/**
 * Context to wire up all components of the application
 *
 * @author priyakdey
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public class ApplicationContext {

    // TODO: Method def and implementation to be changed.
    public ParkingController getController(int numberOfSpots) {
        final List<Module> configurationModules = List.of(new ParkingControllerModule(),
                                                          new ParkingServiceModule(),
                                                          new AvailableSpotStrategyModule(),
                                                          new PricingStrategyModule(),
                                                          new ParkingLotModule(numberOfSpots));

        final Injector injector = Guice.createInjector(configurationModules);
        final ParkingController controller = injector.getInstance(ParkingController.class);
        return controller;
    }

}

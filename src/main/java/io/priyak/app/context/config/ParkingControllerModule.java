package io.priyak.app.context.config;

import com.google.inject.AbstractModule;
import io.priyak.app.controller.ParkingController;
import io.priyak.app.controller.impl.ParkingControllerImpl;

public class ParkingControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ParkingController.class).to(ParkingControllerImpl.class);
    }
}

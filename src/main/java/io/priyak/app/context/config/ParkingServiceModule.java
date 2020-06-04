package io.priyak.app.context.config;

import com.google.inject.AbstractModule;
import io.priyak.app.context.Configuration;
import io.priyak.app.core.service.ParkingService;
import io.priyak.app.core.service.impl.ParkingServiceImpl;

@Configuration
public class ParkingServiceModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ParkingService.class).to(ParkingServiceImpl.class);
    }

}

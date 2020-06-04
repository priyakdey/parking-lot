package io.priyak.app.context.config;

import com.google.inject.AbstractModule;
import io.priyak.app.context.Configuration;
import io.priyak.app.core.common.strategy.AvailableSpotStrategy;
import io.priyak.app.core.common.strategy.impl.NearestAvailableSpotStrategyImpl;

@Configuration
public class AvailableSpotStrategyModule extends AbstractModule {

    @Override
    public void configure() {
        bind(AvailableSpotStrategy.class).to(NearestAvailableSpotStrategyImpl.class);
    }

}

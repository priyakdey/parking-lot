package io.priyak.app.context.config;

import com.google.inject.AbstractModule;
import io.priyak.app.context.Configuration;
import io.priyak.app.core.common.strategy.PricingStrategy;
import io.priyak.app.core.common.strategy.impl.PricingStrategyImpl;

@Configuration
public class PricingStrategyModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PricingStrategy.class).to(PricingStrategyImpl.class);
    }

}

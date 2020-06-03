package io.priyak.app.core.common.strategy;

/**
 * Interface to define various strategies to calculate the Parking Charges
 *
 * @author priyakdey
 */
public interface PricingStrategy {

    Number charges(Number numberOfHours);

}

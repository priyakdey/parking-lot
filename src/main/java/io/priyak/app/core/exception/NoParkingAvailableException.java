package io.priyak.app.core.exception;

/**
 * {@link RuntimeException} type to be thrown when {@link io.priyak.app.core.domain.ParkingLot} is full
 *
 * @author priyakdey
 */
public class NoParkingAvailableException extends RuntimeException {
    private static final long serialVersionUID = 5038004998325415890L;

    public NoParkingAvailableException(String message) {
        super(message);
    }
}

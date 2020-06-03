package io.priyak.app.core.exception;

public class NoVehicleFoundException extends RuntimeException {
    private static final long serialVersionUID = 9221123342281144952L;

    public NoVehicleFoundException(String message) {
        super(message);
    }
}

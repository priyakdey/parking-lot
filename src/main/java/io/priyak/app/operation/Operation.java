package io.priyak.app.operation;

/**
 * Enum to define the set of allowed operations
 *
 * @author priyakdey
 */
public enum Operation {
    CREATE("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status");

    private final String operation;

    Operation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }
}

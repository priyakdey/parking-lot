package io.priyak.app.operation;

import java.util.HashMap;
import java.util.Map;

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

    /* Static instances */
    private static final Map<String, Operation> map = new HashMap<>();

    static {
        map.put("create_parking_lot", CREATE);
        map.put("park", PARK);
        map.put("leave", LEAVE);
        map.put("status", STATUS);
    }


    /**
     * Static method to return the {@link Operation} based on a string value
     * (@param instruction)
     * @return Operation
     */
    public static Operation get(String instruction) {
        return map.get(instruction);
    }
}

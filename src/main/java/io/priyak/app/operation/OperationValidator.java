package io.priyak.app.operation;

import java.util.Objects;
import java.util.Queue;

import static io.priyak.app.operation.Operation.*;

public class OperationValidator {
    /* Class cannot be instantiated */
    private OperationValidator() {}

    public static boolean validateOperationSet(Queue<String> queue) {
        boolean validFirstOperation = validateFirstOperation(queue.peek());
        boolean validOperations = validateOperations(queue);
        return validFirstOperation && validOperations;
    }

    private static boolean validateOperations(Queue<String> queue) {
        int createCount = 0;
        for (String instr : queue) {
            String[] arr = queue.poll().split(" ");

            if (Objects.equals(arr[0], CREATE.getOperation()) && arr.length == 2) {
                if (createCount == 0) {
                    createCount ++;
                    continue;
                } else {
                    return false;
                }
            } else if (Objects.equals(arr[0], PARK.getOperation()) && arr.length == 2) {
                continue;
            } else if(Objects.equals(arr[0], LEAVE.getOperation()) && arr.length == 3) {
                continue;
            } else if (Objects.equals(arr[0], STATUS.getOperation()) && arr.length == 1) {
                continue;
            } else {
                return false;
            }
        }

        return true;
    }

    private static boolean validateFirstOperation(String instr) {
        final String[] arr = instr.split(" ");
        return arr[0].contains("create_parking_lot") && arr.length == 2;
    }
}

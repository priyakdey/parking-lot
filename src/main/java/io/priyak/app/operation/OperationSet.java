package io.priyak.app.operation;

import java.util.Queue;

public abstract class OperationSet {
    private final Queue<String> queue;

    OperationSet(Queue<String> queue) {
        this.queue = queue;
    }
}

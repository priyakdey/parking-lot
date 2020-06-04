package io.priyak.app.controller;

import io.priyak.app.operation.OperationSet;

/**
 * Interface to define a common contract for all to start processing the operations
 *
 * @author priyakdey
 */
public interface ParkingController {
    void process();

    default void setOperationSet(OperationSet operationSet){}
}

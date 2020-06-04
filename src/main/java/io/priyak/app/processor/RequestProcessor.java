package io.priyak.app.processor;

import io.priyak.app.operation.OperationSet;

/**
 * Common interface to define the processing of input
 *
 * @author priyakdey
 */
public interface RequestProcessor {

    OperationSet process() throws Exception;

}

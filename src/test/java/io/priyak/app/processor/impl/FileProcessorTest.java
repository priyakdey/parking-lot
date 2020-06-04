package io.priyak.app.processor.impl;

import io.priyak.app.operation.OperationSet;
import io.priyak.app.processor.RequestProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileProcessorTest {
    RequestProcessor processor;

    @BeforeEach
    void setUp() {
        processor = new FileProcessor(new File("src/test/resources", "input.txt"));
    }
    @Test
    void process() throws Exception {
        final OperationSet operationSet = processor.process();
        assertNotNull(operationSet);
    }
}
package io.priyak.app.operation;

import io.priyak.app.file.FileParserFactory;
import io.priyak.app.file.parser.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Operation Validator Test")
class OperationValidatorTest {
    private static Parser parser;
    private static File validFile;
    private static File inValidFile1;
    private static File inValidFile2;

    @BeforeAll
     static void setUp() {
         parser = FileParserFactory.getParser("txt");
         validFile = new File("src/test/resources", "input.txt");
         inValidFile1 = new File("src/test/resources", "invalid-input1.txt");
         inValidFile2 = new File("src/test/resources", "invalid-input2.txt");
     }

    @Test
    @DisplayName("should return true")
    void validateOperationSetScenario1() throws IOException {
        final Queue<String> queue = parser.parse(validFile);
        final boolean valid = OperationValidator.validateOperationSet(queue);

        assertTrue(valid);
    }

    @Test
    @DisplayName("should return false")
    void validateOperationSetScenario2() throws IOException {
        final Queue<String> queue = parser.parse(inValidFile1);
        final boolean valid = OperationValidator.validateOperationSet(queue);

        assertFalse(valid);
    }

    @Test
    @DisplayName("should return false")
    void validateOperationSetScenario3() throws IOException {
        final Queue<String> queue = parser.parse(inValidFile2);
        final boolean valid = OperationValidator.validateOperationSet(queue);

        assertFalse(valid);
    }
}
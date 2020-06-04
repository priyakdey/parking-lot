package io.priyak.app.processor.impl;

import io.priyak.app.file.FileParserFactory;
import io.priyak.app.file.FileValidator;
import io.priyak.app.file.InvalidFileException;
import io.priyak.app.file.parser.Parser;
import io.priyak.app.operation.InvalidOperationException;
import io.priyak.app.operation.OperationSet;
import io.priyak.app.operation.OperationSetImpl;
import io.priyak.app.operation.OperationValidator;
import io.priyak.app.processor.RequestProcessor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class FileProcessor implements RequestProcessor {
    private static final String INVALID_FILE = "Please enter a valid non-empty file";
    private static final String INVALID_OPERATION=
            "First operation should be create_parking_lot. Valid instructions are: create_parking_lot, park, leave, status";
    private final File file;


    public FileProcessor(File file) {
        this.file = file;
    }

    @Override
    public OperationSet process() throws InvalidFileException, IOException, InvalidOperationException {
        final boolean isValid = FileValidator.validateFile(file);
        if (!isValid) {
            throw new InvalidFileException(INVALID_FILE);
        }

        final Parser parser = FileParserFactory.getParser(file.getName());
        Queue<String> queue = parser.parse(file);

        boolean validOperations = OperationValidator.validateOperationSet(new ArrayDeque<>(queue));

        if (!validOperations) {
            throw new InvalidOperationException(INVALID_OPERATION);
        }

        return new OperationSetImpl(queue);
    }

}

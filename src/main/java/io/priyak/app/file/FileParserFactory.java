package io.priyak.app.file;

import io.priyak.app.file.parser.Parser;
import io.priyak.app.file.parser.TxtParser;

public class FileParserFactory {

    /* class should not be instantiated */
    private FileParserFactory() {}

    public static Parser getParser(String name) {
        if (name.endsWith("txt")) {
            return new TxtParser();
        } else {
            throw new UnsupportedOperationException("File Extension not supported yet.");
        }
    }

}

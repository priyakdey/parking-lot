package io.priyak.app.file.parser;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

public interface Parser {

    Queue<String> parse(File file) throws IOException;

}

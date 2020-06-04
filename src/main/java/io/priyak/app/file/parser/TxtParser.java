package io.priyak.app.file.parser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.Queue;

public class TxtParser implements Parser {

    @Override
    public Queue<String> parse(File file) throws IOException {
       Queue<String> queue = new ArrayDeque<>();
       Files.lines(file.toPath())
               .forEach(queue::add);

       return queue;
    }
}

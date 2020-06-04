package io.priyak.app.file.parser;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class TxtParserTest {
    Parser parser = new TxtParser();

    @Test
    void parse() throws IOException {
        File file = new File("src/test/resources", "input.txt");
        final Queue<String> queue = parser.parse(file);

        assertAll(
                () -> assertNotNull(queue),
                () -> assertEquals(18, queue.size())
        );
    }
}
package io.priyak.app.file;

import java.io.File;

public class FileValidator {

    /* Class should not be instantiated */
    private FileValidator() {}

    public static boolean validateFile(File file) {
        final boolean exists = file.exists();
        final boolean isFile = file.isFile();
        final boolean isNotEmpty = file.length() != 0;

        return exists && isFile && isNotEmpty;
    }
}

package io.priyak.app.file;

public class InvalidFileException extends Exception {

    private static final long serialVersionUID = 633816822858131810L;

    public InvalidFileException(String message) {
        super(message);
    }
}

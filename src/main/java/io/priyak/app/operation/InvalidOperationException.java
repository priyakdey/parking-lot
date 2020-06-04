package io.priyak.app.operation;

public class InvalidOperationException extends Exception {

    private static final long serialVersionUID = 6550314481767407755L;

    public InvalidOperationException(String message) {
        super(message);
    }
}

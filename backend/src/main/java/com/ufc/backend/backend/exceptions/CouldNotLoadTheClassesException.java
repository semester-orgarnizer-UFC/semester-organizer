package com.ufc.backend.backend.exceptions;

public class CouldNotLoadTheClassesException extends RuntimeException {
    public CouldNotLoadTheClassesException() {
        super("Could not load the classes");
    }

    public CouldNotLoadTheClassesException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
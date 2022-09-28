package com.ufc.backend.backend.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("Forbidden exception");
    }

    public ForbiddenException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

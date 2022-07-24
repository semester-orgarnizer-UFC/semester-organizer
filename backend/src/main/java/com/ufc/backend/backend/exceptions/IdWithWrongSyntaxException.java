package com.ufc.backend.backend.exceptions;

public class IdWithWrongSyntaxException extends RuntimeException {
    public IdWithWrongSyntaxException(String id) {
        super("The given id: |" + id + "| is wrong, it should be: QXDXXXX, replace X for numbers");
    }
    public IdWithWrongSyntaxException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

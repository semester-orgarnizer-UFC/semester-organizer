package com.ufc.backend.backend.exceptions;

public class IdAlreadyExists extends RuntimeException {
    public IdAlreadyExists(String id) {
        super("The given id: |" + id + "| already exists in the database");
    }
    public IdAlreadyExists(String msg, Throwable cause) {
        super(msg, cause);
    }
}

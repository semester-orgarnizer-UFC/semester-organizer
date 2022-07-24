package com.ufc.backend.backend.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String id) {
        super("The given id: |" + id + "| was not found");
    }
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

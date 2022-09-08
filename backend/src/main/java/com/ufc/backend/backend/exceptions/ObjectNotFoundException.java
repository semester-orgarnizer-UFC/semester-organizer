package com.ufc.backend.backend.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String id) {
        super("O id: |" + id + "| não foi encontrado");
    }
    public ObjectNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

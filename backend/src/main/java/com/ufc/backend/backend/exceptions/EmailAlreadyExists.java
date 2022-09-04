package com.ufc.backend.backend.exceptions;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String email) {
        super("O email " + email + "já está sendo usado");
    }
    public EmailAlreadyExists(String msg, Throwable cause) {
        super(msg, cause);
    }
}

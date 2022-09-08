package com.ufc.backend.backend.exceptions;


public class SemesterOutOfBoundsException extends RuntimeException {
    public SemesterOutOfBoundsException() {
        super("Esse semestre não está disponível");
    }

    public SemesterOutOfBoundsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
package com.ufc.backend.backend.exceptions;


public class SemesterOutOfBoundsException extends RuntimeException {
    public SemesterOutOfBoundsException(Integer index) {
        super("The index " + index + " is out of bounds, the semester is not available");
    }

    public SemesterOutOfBoundsException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
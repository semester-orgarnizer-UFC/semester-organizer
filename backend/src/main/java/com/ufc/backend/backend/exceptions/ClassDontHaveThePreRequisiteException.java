package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassDontHaveThePreRequisiteException extends RuntimeException {
    public ClassDontHaveThePreRequisiteException(Classes classes) {
        super("You can't do this class now: " + classes.getName() + ", You have to do the pre requisite first: " + classes.getPreRequisite().getName());
    }

    public ClassDontHaveThePreRequisiteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
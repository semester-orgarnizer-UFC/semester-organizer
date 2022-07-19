package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassesAndPreRequisiteAtTheSameTimeException extends RuntimeException {
    public ClassesAndPreRequisiteAtTheSameTimeException(Classes classes) {
        super("You can't do: " + classes.getName() + " and " + classes.getPreRequisite().getName() + " at the same semester");
    }

    public ClassesAndPreRequisiteAtTheSameTimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
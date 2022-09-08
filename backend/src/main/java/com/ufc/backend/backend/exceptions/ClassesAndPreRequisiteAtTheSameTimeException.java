package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassesAndPreRequisiteAtTheSameTimeException extends RuntimeException {
    public ClassesAndPreRequisiteAtTheSameTimeException(Classes classes) {
        super("Você não pode cursar: " + classes.getName() + " e " + classes.getPreRequisite().getName() + " no mesmo semestre");
    }

    public ClassesAndPreRequisiteAtTheSameTimeException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
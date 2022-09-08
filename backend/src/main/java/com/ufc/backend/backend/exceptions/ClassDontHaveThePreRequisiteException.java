package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassDontHaveThePreRequisiteException extends RuntimeException {
    public ClassDontHaveThePreRequisiteException(Classes classes) {
        super("Você não pode cursar essa cadeira: " + classes.getName() + ", Você precisa cursar o pré requisito dela antes: " + classes.getPreRequisite().getName());
    }

    public ClassDontHaveThePreRequisiteException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
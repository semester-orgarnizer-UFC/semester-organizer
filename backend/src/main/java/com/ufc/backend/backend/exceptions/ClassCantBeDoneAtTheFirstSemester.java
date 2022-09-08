package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassCantBeDoneAtTheFirstSemester extends RuntimeException {
    public ClassCantBeDoneAtTheFirstSemester(Classes classes) {
        super("Você não pode cursar essa cadeira no primeiro semestre: " + classes.getName());
    }

    public ClassCantBeDoneAtTheFirstSemester(String msg, Throwable cause) {
        super(msg, cause);
    }
}
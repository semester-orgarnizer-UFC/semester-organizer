package com.ufc.backend.backend.exceptions;

import com.ufc.backend.backend.model.Classes;

public class ClassCantBeDoneAtTheFirstSemester extends RuntimeException {
    public ClassCantBeDoneAtTheFirstSemester(Classes classes) {
        super("You can't do this class at the first semester: " + classes.getName());
    }

    public ClassCantBeDoneAtTheFirstSemester(String msg, Throwable cause) {
        super(msg, cause);
    }
}
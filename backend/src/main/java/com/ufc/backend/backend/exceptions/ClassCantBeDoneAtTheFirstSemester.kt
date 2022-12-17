package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes

class ClassCantBeDoneAtTheFirstSemester : RuntimeException {
    constructor(classes: Classes) : super("Você não pode cursar essa cadeira no primeiro semestre: $classes."
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
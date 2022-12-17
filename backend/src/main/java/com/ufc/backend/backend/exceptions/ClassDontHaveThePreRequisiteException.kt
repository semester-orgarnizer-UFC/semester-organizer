package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes

class ClassDontHaveThePreRequisiteException : RuntimeException {
    constructor(classes: Classes) : super(
        "Você não pode cursar essa cadeira: " + classes.getName() + ", Você precisa cursar o pré requisito dela antes: " + classes.getPreRequisite()
            .getName()
    )

    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
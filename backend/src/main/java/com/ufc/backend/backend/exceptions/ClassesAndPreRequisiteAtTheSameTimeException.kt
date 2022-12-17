package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes

class ClassesAndPreRequisiteAtTheSameTimeException : RuntimeException {
    constructor(classes: Classes) : super(
        "Você não pode cursar: " + classes.getName() + " e " + classes.getPreRequisite()
            .getName() + " no mesmo semestre"
    )

    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
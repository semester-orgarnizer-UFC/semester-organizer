package com.ufc.backend.backend.exceptions

class IdWithWrongSyntaxException : RuntimeException {
    constructor(id: String) : super("The given id: |$id| is wrong, it should be: QXDXXXX, replace X for numbers")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
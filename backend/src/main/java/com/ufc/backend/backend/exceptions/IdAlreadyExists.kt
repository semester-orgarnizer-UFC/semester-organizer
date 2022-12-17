package com.ufc.backend.backend.exceptions

class IdAlreadyExists : RuntimeException {
    constructor(id: String) : super("The given id: |$id| already exists in the database")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
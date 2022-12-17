package com.ufc.backend.backend.exceptions

class EmailAlreadyExists : RuntimeException {
    constructor(email: String) : super("O email $email já está sendo usado")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
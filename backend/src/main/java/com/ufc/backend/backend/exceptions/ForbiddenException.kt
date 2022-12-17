package com.ufc.backend.backend.exceptions

class ForbiddenException : RuntimeException {
    constructor() : super("Forbidden exception")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
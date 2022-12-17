package com.ufc.backend.backend.exceptions

class SemesterOutOfBoundsException : RuntimeException {
    constructor() : super("Esse semestre não está disponível")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
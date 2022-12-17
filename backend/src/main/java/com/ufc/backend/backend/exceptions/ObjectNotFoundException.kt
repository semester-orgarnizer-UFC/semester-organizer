package com.ufc.backend.backend.exceptions

class ObjectNotFoundException : RuntimeException {
    constructor(id: String) : super("O id: |$id| não foi encontrado")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
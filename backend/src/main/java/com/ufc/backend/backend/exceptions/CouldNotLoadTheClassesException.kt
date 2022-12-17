package com.ufc.backend.backend.exceptions

class CouldNotLoadTheClassesException : RuntimeException {
    constructor() : super("Could not load the classes")
    constructor(msg: String?, cause: Throwable?) : super(msg, cause)
}
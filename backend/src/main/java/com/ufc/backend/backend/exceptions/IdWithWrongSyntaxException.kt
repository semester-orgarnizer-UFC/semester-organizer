package com.ufc.backend.backend.exceptions

class IdWithWrongSyntaxException(id: String) :
    RuntimeException("The given id: |$id| is wrong, it should be: QXDXXXX, replace X for numbers")
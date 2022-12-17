package com.ufc.backend.backend.exceptions

class IdAlreadyExists(id: String) : RuntimeException("The given id: |$id| already exists in the database")

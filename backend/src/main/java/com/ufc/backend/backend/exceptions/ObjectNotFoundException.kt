package com.ufc.backend.backend.exceptions

class ObjectNotFoundException(id: String) : RuntimeException("O id: |$id| não foi encontrado")

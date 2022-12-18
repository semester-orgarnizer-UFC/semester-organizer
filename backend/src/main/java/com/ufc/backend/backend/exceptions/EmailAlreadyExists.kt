package com.ufc.backend.backend.exceptions

class EmailAlreadyExists(email: String) : RuntimeException("O email $email já está sendo usado")
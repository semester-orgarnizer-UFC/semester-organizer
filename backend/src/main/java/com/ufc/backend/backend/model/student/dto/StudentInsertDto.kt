package com.ufc.backend.backend.model.student.dto

data class StudentInsertDto(
    val firstName: String,
    val lastName: String,
    var password: String,
    val email: String,
    val course: String
)

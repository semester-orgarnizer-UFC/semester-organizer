package com.ufc.backend.backend.model.student.dto

data class StudentDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val course: String
)

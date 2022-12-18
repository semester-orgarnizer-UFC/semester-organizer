package com.ufc.backend.backend.resources.exceptions

data class GenericError(
    private val timestamp: Long,
    private val status: Int,
    private val error: String,
    private val message: String,
    private val path: String,
)

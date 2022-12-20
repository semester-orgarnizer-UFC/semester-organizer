package com.ufc.backend.backend.resources.exceptions

import com.fasterxml.jackson.databind.ObjectMapper

data class GenericError(
    val timestamp: Long,
    val status: Int,
    val error: String,
    val message: String,
    val path: String,
) {
    override fun toString(): String {
        return ObjectMapper().writeValueAsString(this)
    }
}

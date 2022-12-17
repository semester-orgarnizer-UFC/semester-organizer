package com.ufc.backend.backend.commos

import java.io.Serializable

interface Identifiable<ID : Serializable> {
    val id: ID
}
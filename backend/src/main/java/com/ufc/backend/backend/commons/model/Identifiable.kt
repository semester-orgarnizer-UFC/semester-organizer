package com.ufc.backend.backend.commons.model

import java.io.Serializable

interface Identifiable<ID : Serializable> {
    val id: ID
}
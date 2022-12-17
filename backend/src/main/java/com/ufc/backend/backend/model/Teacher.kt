package com.ufc.backend.backend.model

import com.ufc.backend.backend.model.based.PersonBased
import org.springframework.data.mongodb.core.mapping.Document

@Document
class Teacher : PersonBased()
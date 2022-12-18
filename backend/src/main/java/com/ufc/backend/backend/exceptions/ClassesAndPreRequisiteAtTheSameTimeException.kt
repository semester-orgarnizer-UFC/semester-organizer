package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Subject

class ClassesAndPreRequisiteAtTheSameTimeException(subject: Subject) : RuntimeException(
    "Você não pode cursar: ${subject.name} e ${subject.preRequisite?.name} ao mesmo tempo"
)

package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Subject

class ClassDontHaveThePreRequisiteException(subject: Subject) : RuntimeException(
    "Você não pode cursar essa cadeira: ${subject.name}, você precisa cursar o pré requisito dela antes: ${subject.preRequisite?.name}"
)

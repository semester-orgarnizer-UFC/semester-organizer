package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Subject
class ClassCantBeDoneAtTheFirstSemester(subject: Subject) :
    RuntimeException("Você não pode cursar essa cadeira no primeiro semestre: ${subject.name}")

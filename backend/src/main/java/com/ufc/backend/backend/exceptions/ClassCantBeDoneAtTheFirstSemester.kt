package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes
class ClassCantBeDoneAtTheFirstSemester(classes: Classes) :
    RuntimeException("Você não pode cursar essa cadeira no primeiro semestre: ${classes.name}")

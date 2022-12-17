package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes

class ClassDontHaveThePreRequisiteException(classes: Classes) : RuntimeException(
    "Você não pode cursar essa cadeira: ${classes.name}, você precisa cursar o pré requisito dela antes: ${classes.preRequisite?.name}"
)

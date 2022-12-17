package com.ufc.backend.backend.exceptions

import com.ufc.backend.backend.model.Classes

class ClassesAndPreRequisiteAtTheSameTimeException(classes: Classes) : RuntimeException(
    "Você não pode cursar: ${classes.name} e ${classes.preRequisite?.name} ao mesmo tempo"
)

package com.ufc.backend.backend.model.feedback;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Rating {

    private double howDifficultItWasToLearn;
    private double isThereALotOfWorksToDo;
    private int howManyExams;
}

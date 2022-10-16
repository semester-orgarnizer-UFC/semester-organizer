package com.ufc.backend.backend.model.feedback;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {

    @Id
    private String id;
    private String commentary;
    private Rating rating;
    @DBRef
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Classes classes;
    private Student user;
    @JsonIgnore
    private Student actualUser;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean isAnonymous;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass()) {
            return false;
        }
        return this.getId().equals(((Feedback) obj).getId());
    }
}

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
class Rating {

    private double howDifficultItWasToLearn;
    private double isThereALotOfWorksToDo;
    private int howManyExams;
}


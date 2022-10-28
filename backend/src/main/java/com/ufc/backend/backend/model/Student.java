package com.ufc.backend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.ufc.backend.backend.model.based.PersonBased;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
public class Student extends PersonBased {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String course;
    private List<Semester> semester = new ArrayList<>();
    @JsonIgnore
    private Set<Classes> notTakenClasses = new HashSet<>();

    public void addEmptySemester() {
        int index = this.semester.isEmpty() ? 1 : this.getSemester().size() + 1;
        this.getSemester().add(new Semester(index, null));
    }

    public void updateSemester(Semester newSemester) {
        Semester oldSemester = this.getSemester().get(newSemester.getIndex() - 1);
        oldSemester.getClasses().addAll(newSemester.getClasses());

        notifyTakenClasses(newSemester.getClasses());
        notifyNotTakenClasses(newSemester.getClasses());
    }

    private void notifyTakenClasses(Set<Classes> classes) {
        getClasses().addAll(classes);
    }

    private void notifyNotTakenClasses(Set<Classes> classes) {
        getNotTakenClasses().removeAll(classes);
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() != obj.getClass())
            return false;

        return this.getId().equals(((Student) obj).getId());
    }
}

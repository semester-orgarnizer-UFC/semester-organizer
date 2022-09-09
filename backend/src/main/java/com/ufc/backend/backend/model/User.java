package com.ufc.backend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
@ToString
public class User {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @DBRef
    @JsonIgnore
    private Course course;
    private List<Semester> semester;

    public void addEmptySemester() {
        if (this.getSemester() == null) {
            this.setSemester(List.of(new Semester(1, null)));
        } else {
            this.getSemester().add(new Semester(this.getSemester().size() + 1, null));
        }
    }

    public void updateSemester(Semester newSemester) {
        Semester oldSemester = this.getSemester().get(newSemester.getIndex() - 1);

        if (oldSemester.getClasses() == null) {
            oldSemester.setClasses(newSemester.getClasses());
        } else oldSemester.getClasses().addAll(newSemester.getClasses());
    }
}

package com.ufc.backend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import static org.springframework.util.StringUtils.capitalize;
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
    private String name;
    private String surname;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String fullName = capitalize(name) + " " + capitalize(surname);
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @DBRef
    @JsonIgnore
    private Course course;
    private List<Semester> semester;
    private Integer currentSemester;

}

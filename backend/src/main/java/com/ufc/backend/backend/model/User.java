package com.ufc.backend.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @DBRef
    @JsonIgnore
    private Course course;
    private List<Semester> semester;
    private Integer currentSemester;

}

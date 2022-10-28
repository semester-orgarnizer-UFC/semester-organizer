package com.ufc.backend.backend.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Semester {
    private Integer index;
    @DBRef
    private Set<Classes> classes = new HashSet<>();
    public boolean equals(Semester obj) {
        return this.getIndex().equals(obj.getIndex());
    }
}

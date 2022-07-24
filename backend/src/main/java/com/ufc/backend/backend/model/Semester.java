package com.ufc.backend.backend.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Semester {
    private Integer index;
    @DBRef
    private List<Classes> classes;
}

package com.ufc.backend.backend.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufc.backend.backend.model.Course;
import com.ufc.backend.backend.repositories.ClassRepository;
import com.ufc.backend.backend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.util.Objects;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private ClassRepository classRepository;


    public void populateCourses() {
        try {
            File in = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("courses/cc.json")).getFile());
            ObjectMapper mapper = new ObjectMapper();
            // Necessary to not fail when accessing the properties : ref, id. for MongoDB relation
            mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            Course course = mapper.readValue(in, Course.class);
            course.getMandatoryClasses().forEach(obj -> classRepository.save(obj));
            courseRepository.save(mapper.readValue(in, Course.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

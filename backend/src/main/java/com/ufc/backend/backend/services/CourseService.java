package com.ufc.backend.backend.services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufc.backend.backend.exceptions.CouldNotLoadTheClassesException;
import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.Course;
import com.ufc.backend.backend.repositories.ClassRepository;
import com.ufc.backend.backend.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
            classRepository.saveAll(course.getMandatoryClasses());
            courseRepository.save(mapper.readValue(in, Course.class));
        } catch (Exception e) {
            throw new CouldNotLoadTheClassesException();
        }
    }

    public Course findById(String id) {
        return courseRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    public List<Classes> findMandatory(String id) {
        return findById(id).getMandatoryClasses();
    }

    public List<Classes> findOptional(String id) {
        return findById(id).getOptionalClasses();
    }

    public List<Classes> findBySemester(String id, Integer semester) {
        return findById(id).getMandatoryClasses().stream().filter(obj -> Objects.equals(obj.getSemester(), semester)).collect(Collectors.toList());
    }

}

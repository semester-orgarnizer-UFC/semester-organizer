package com.ufc.backend.backend;

import com.ufc.backend.backend.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class BackendApplication {

    @Autowired
    private CourseService courseService;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void populateCourse() {
        courseService.populateCourses();
    }
}
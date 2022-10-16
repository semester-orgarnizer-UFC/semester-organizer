package com.ufc.backend.backend.services;

import com.ufc.backend.backend.exceptions.ObjectNotFoundException;
import com.ufc.backend.backend.model.Classes;
import com.ufc.backend.backend.model.User;
import com.ufc.backend.backend.model.feedback.Feedback;
import com.ufc.backend.backend.repositories.ClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatsService {
    @Autowired
    private UserService userService;

    /**
     *
     * @return number of semesters in the system
     */
    public Integer numberOfSemesters(){
        return userService.findAll().stream().filter(user -> user.getSemester() != null).mapToInt(user -> user.getSemester().size()).sum();
    }

}
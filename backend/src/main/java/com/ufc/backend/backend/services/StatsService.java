package com.ufc.backend.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
package com.ufc.backend.backend.services

import com.ufc.backend.backend.model.Student
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class StatsService {
    @Autowired
    private val userService: UserService? = null

    /**
     *
     * @return number of semesters in the system
     */
    fun numberOfSemesters(): Int {
        return userService!!.findAll().stream().filter { user: Student? -> user.getSemester() != null }
            .mapToInt { user: Student? -> user.getSemester().size() }.sum()
    }
}
package com.ufc.backend.backend.services

import org.springframework.stereotype.Service

@Service
class StatsService (
    private val userService: UserService
){

    /**
     *
     * @return number of semesters in the system
     */
    fun numberOfSemesters(): Int? {
        return null
    }
}
package com.ufc.backend.backend

import com.ufc.backend.backend.services.CourseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener

@SpringBootApplication
class BackendApplication {
    @Autowired
    private val courseService: CourseService? = null
    @EventListener(ApplicationReadyEvent::class)
    fun populateCourse() {
        courseService!!.populateCourses()
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BackendApplication::class.java, *args)
        }
    }
}
package com.ufc.backend.backend.resources

import com.ufc.backend.backend.security.JwtTokenUtil
import com.ufc.backend.backend.security.services.UserDetailsService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin
class AuthResource(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: UserDetailsService,
    private val jwtTokenUtil: JwtTokenUtil
) {
}
package com.ufc.backend.backend.security.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.ufc.backend.backend.security.model.LoginDto
import com.ufc.backend.backend.resources.exceptions.GenericError
import com.ufc.backend.backend.security.JwtTokenUtil
import com.ufc.backend.backend.security.model.UserSecurity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter(
    private val jwtTokenUtil: JwtTokenUtil,
    private val authenticationManager: AuthenticationManager
) :
    UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(req: HttpServletRequest, response: HttpServletResponse): Authentication {
        val credentials = ObjectMapper().readValue(req.inputStream, LoginDto::class.java)
        val authToken = UsernamePasswordAuthenticationToken(
            credentials.email,
            credentials.password,
            listOf()
        )
        return authenticationManager.authenticate(authToken)
    }

    override fun successfulAuthentication(
        req: HttpServletRequest?, res: HttpServletResponse, chain: FilterChain?,
        auth: Authentication
    ) {
        val username = (auth.principal as UserSecurity).username
        val token: String = jwtTokenUtil.generateToken(username)
        res.addHeader("Authorization", token)
        res.addHeader("Access-Control-Expose-Headers", "Authorization")
    }

    override fun unsuccessfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        val error = GenericError(
            Date().time,
            401, "Bad credentials",
            "Email ou senha incorretos",
            "/login"
        )
        response.status = error.status
        response.contentType = "application/json"
        response.writer.append(error.toString())
    }

}
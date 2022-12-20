package com.ufc.backend.backend.security.services

import com.ufc.backend.backend.repositories.UserRepository
import com.ufc.backend.backend.security.model.UserSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import java.util.*
@Service
class UserDetailsService(
    private val repository: UserRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByPersonEmail(username) ?: throw UsernameNotFoundException("$username not found")
        return UserSecurity(
            user.id,
            user.person.email,
            user.password,
            Collections.singleton(SimpleGrantedAuthority("user"))
        )
    }
}
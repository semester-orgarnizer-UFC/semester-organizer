package com.ufc.backend.backend.security

import com.ufc.backend.backend.security.filters.JwtAuthenticationFilter
import com.ufc.backend.backend.security.services.UserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain


@EnableWebSecurity
@Configuration
open class SecurityConfig(
    private val userDetailsService: UserDetailsService,
) {

    @Bean
    open fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.authorizeRequests().antMatchers("/users")
            .permitAll().anyRequest().authenticated().and().csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .addFilter(JwtAuthenticationFilter(JwtTokenUtil(), authenticationManager(AuthenticationConfiguration())))
        return http.build()
    }

    @Bean
    open fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    open fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<org.springframework.security.core.userdetails.UserDetailsService>(userDetailsService)
            .passwordEncoder(bCryptPasswordEncoder())
    }


    @Bean
    open fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }
}
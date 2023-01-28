package com.ufc.backend.backend.commons.model

import com.ufc.backend.backend.security.model.UserSecurity
import org.springframework.security.core.context.SecurityContextHolder

object SecurityUtils {
    val authenticatedUser = SecurityContextHolder.getContext().authentication.principal as UserSecurity
}
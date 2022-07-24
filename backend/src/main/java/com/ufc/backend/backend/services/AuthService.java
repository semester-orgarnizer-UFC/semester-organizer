package com.ufc.backend.backend.services;

import com.ufc.backend.backend.security.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    public static UserSecurity userAuthenticated(){
        return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

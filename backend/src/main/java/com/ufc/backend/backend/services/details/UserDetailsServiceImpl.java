package com.ufc.backend.backend.services.details;

import com.ufc.backend.backend.model.Student;
import com.ufc.backend.backend.repositories.UserRepository;
import com.ufc.backend.backend.security.UserSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Student user = repository.findByEmail(email);
        return new UserSecurity(user.getId(), user.getEmail(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("user")));
    }
}

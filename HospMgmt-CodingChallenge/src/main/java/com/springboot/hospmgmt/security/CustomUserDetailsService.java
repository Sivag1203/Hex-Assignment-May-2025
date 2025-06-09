package com.springboot.hospmgmt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.springboot.hospmgmt.model.Auth;
import com.springboot.hospmgmt.repository.AuthRepository;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthRepository authRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Auth auth = authRepository.findByName(name);
        if (auth == null)
            throw new UsernameNotFoundException("User not found with name: " + name);

        return new User(
                auth.getName(),
                auth.getPassword(),
                List.of(new SimpleGrantedAuthority(auth.getRole())) 
        );
    }
}

package com.sumit.learning.service;

import com.sumit.learning.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginSignUpService loginSignUpService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
    public UserDetails loadUserByUserEmail(String email)
    {
        UserEntity userEntity=loginSignUpService.findUserByEmailId(email);

        return null;
    }
}
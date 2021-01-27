package com.sumit.learning.Utils;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.annotation.WebServlet;

@Configuration
public class Bcrypt {

    @Bean
    public PasswordEncoder encoder()
    {
        return new BCryptPasswordEncoder();
    }


}

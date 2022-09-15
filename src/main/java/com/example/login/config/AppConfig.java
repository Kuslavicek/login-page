package com.example.login.config;

import com.example.login.Dto.UserForm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public static UserForm newForm(){
        return new UserForm();
    }
}

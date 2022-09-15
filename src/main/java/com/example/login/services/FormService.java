package com.example.login.services;

import com.example.login.Dto.UserForm;
import com.example.login.config.AppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class FormService {

    private UserForm newForm = AppConfig.newForm();
}

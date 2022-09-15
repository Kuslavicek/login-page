package com.example.login.Dto;

import org.springframework.stereotype.Component;

@Component
public class UserForm {

    private String name;

    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLegit(){
        if(name != null && name.trim().length()!=0 && password != null && password.trim().length()!=0){
            return true;
        }else{
            return false;
        }
    }
}

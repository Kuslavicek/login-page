package com.example.login.controller;

import com.example.login.Dto.UserForm;
import com.example.login.data.Rights;
import com.example.login.data.User;
import com.example.login.data.UserRepository;
import com.example.login.services.AccessService;
import com.example.login.services.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Controller("/")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessService accessService;

    @Autowired
    private FormService formService;

    private BCryptPasswordEncoder encoder= new BCryptPasswordEncoder();


    @RequestMapping("/")
    public String loginPageRequest(String name, Model model){
        model.addAttribute("newForm",new UserForm());
        return "login";
    }

    @RequestMapping("/register")
    public String registerRequest(String name,Model model){
        model.addAttribute("newForm", new UserForm());
        return "register";
    }

    @RequestMapping("/register-process")
    public String registerProcessRequest(@ModelAttribute("newForm") UserForm form, Model model){
        if(form.isLegit()==false){
            return "bad-register";
        }else{
            User user =new User();
            user.setName(form.getName());
            user.setPassword(encoder.encode(form.getPassword()));
            user.setRights(Rights.USER);
            userRepository.save(user);
            return "redirect:/";
        }

    }


    @RequestMapping("/login")
    public String loginRequest(String name,Model model){
        model.addAttribute("newForm",new UserForm());
        return "redirect:/";
    }

    @RequestMapping("/login-process")
    public String loginProcessRequest(@ModelAttribute("newForm") UserForm form, Model model) {
        List<User> users = userRepository.findByName(form.getName());
        if (users.isEmpty() || users.get(0).getPassword()!= encoder.encode(form.getPassword())) {
            return "redirect:/bad-login";
        } else {
            User user = users.get(0);
            if (user.getRights() == Rights.ADMIN) {
                return "admin";
            } else {
                return "basic";
            }

        }
    }

    @RequestMapping("/admin")
    public String adminRequest(){
        if(accessService.isAdmin() && accessService.isLogged()){
            return "admin";
        }else{
            return "restricted-admin";
        }
    }

    @RequestMapping("/basic")
    public String basicUserRequest(){
        if(accessService.isLogged()){
            return "basic";
        }else{
            return "restricted";
        }

    }


}

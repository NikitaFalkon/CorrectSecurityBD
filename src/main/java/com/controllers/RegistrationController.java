package com.controllers;

import com.Model.Role;
import com.Model.User;
import com.Model.UserService;
import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {
    /*@Autowired
    UserRepository userRepository;*/
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
@GetMapping("/registration")
public String registration(Model model)
{
    model.addAttribute("userForm", new User());
    return "registration";
}
@PostMapping("/registration")
public String addUser(@ModelAttribute("userForm") @Valid User userForm, Model model)
    {
        if (!userService.NewUser(userForm)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }
        return "redirect:/";
        //User user1 = userRepository.findByUsername(userForm.getUsername());

/*        System.out.println(userForm.getUsername());
        if(user1 == null)
        {
            userForm.setRoles(Collections.singleton(Role.ADMIN));
            userForm.setActive(true);
            userForm.setPassword(passwordEncoder.encode(userForm.getPassword()));
            userRepository.save(userForm);
        }*/
    }
}

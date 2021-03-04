package com.controllers;

import com.Model.Role;
import com.Model.User;
import com.Model.UserService;
import com.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping("/")
    public String userList(Model model)
    {
        model.addAttribute("users", userService.allUsers());
        return "Users";
    }
    @GetMapping("/{id}/edit")
    public String Edit(@PathVariable Long id, Model model)
    {
        if (!userService.ExistById(id))
        {
            return "redirect:hello";
        }
        Optional<User> user = userService.FindById(id);
        ArrayList<User> users = new ArrayList<>();
        user.ifPresent(users::add);
        model.addAttribute("roles", Role.values());
        model.addAttribute("users", users);
        return "Edit";
    }
    @PostMapping("/{id}/edit")
    //public String Editing(@ModelAttribute("user") @Valid User userForm, Model model)
    public String Redacting(@PathVariable(name = "id") long id,
                            @RequestParam(name = "username", required = false) String username,
                            @RequestParam(name = "password", required = false) String password,
//                            @RequestParam(name ="role", required = false) String form,
                            Model model)
    {
        ;
        if (!userService.redactUser(username,password, id)){
            model.addAttribute("usernameError", "There is no such a user");
            return "redirect:/{id}/edit";
        }
        return "redirect:/";
        /*User user = userRepository.findById(id).orElseThrow();
        user.setUsername(username);
        user.setPassword(password);
        user.setActive(true);*/
//        System.out.println(form);
        //form.forEach(f -> System.out.println(f));
/*        Set<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .collect(Collectors.toSet());
        user.getRoles().clear();

        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }*/
        //userRepository.save(user);
    }
}

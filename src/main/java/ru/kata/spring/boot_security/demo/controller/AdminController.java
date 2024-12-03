package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.entetie.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;


@Controller
@RequestMapping("/admin")
public class AdminController {
  final private UserService userService;
   final private RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String showAllUsers(Model model, Principal principal) {
        User user = userService.getUserByEmail(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("helloUser", principal.getName());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("newUser", new User());
        model.addAttribute("role",roleService.getRoles());
        return "admin/adminPanel";
    }
    @PostMapping("/update")
    public String updateUser (@ModelAttribute("updateUser") User user) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute User user, BindingResult bindingResult, Model model) {
    if (bindingResult.hasErrors()){
       bindingResult.getAllErrors().forEach(objectError -> model.addAttribute(objectError.getObjectName(), objectError));
        return "admin/adminPanel";
    }
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}")
    public String deleteUser (@PathVariable("id") long id) {
       userService.deleteUserById(id);
        return "redirect:/admin";
    }



}



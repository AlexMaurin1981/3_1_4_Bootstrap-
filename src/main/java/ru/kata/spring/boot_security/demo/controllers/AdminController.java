package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.enteties.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

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

    @GetMapping

    public String showAllUsers(Model model, Principal principal) {
        model.addAttribute("helloUser", principal.getName());
        model.addAttribute("allUsers", userService.getAllUsers());
        model.addAttribute("role",roleService.getRoles());
        return "admin/adminPanel";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model, Principal principal) {
        model.addAttribute("helloUser", principal.getName());
        model.addAttribute("user", new User());
        model.addAttribute("roles",roleService.getRoles());
        return "admin/adduser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user, BindingResult bindingResult) {
if (bindingResult.hasErrors()){
    return "admin/adduser";
}
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/deleteUser/{id}")
    public String delete (@PathVariable("id") long id,Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());

        return "/admin/deleteuser";
    }
    @PostMapping("/delete/{id}")
    public String deleteUser (@ModelAttribute("user") User user, @PathVariable("id") long id) {
       userService.deleteUserById(id);
        return "redirect:/admin";
    }

    @GetMapping ("/updateUser/{id}")
    public String  update(@PathVariable ("id") long id,Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.getRoles());
        return "admin/updateuser";
    }

        @PostMapping("/update/{id}")
        public String save (@ModelAttribute("user") User user) {
            userService.updateUser(user);
            return "redirect:/admin";
        }
}



package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.entetie.User;

import java.util.List;

public interface UserService  extends UserDetailsService {
    User getUserByEmail(String email);

      void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(User user);
    List<User> getAllUsers();

}


package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.enteties.User;

import java.util.List;

public interface UserService  extends UserDetailsService {
    User getUserByEmail(String email);

      void saveUser(User user);

    User getUserById(Long id);

    void deleteUserById(Long id);

    void updateUser(User user);
    List<User> getAllUsers();

}


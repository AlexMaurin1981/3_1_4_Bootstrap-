package ru.kata.spring.boot_security.demo.reposotorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entetie.Role;

@Repository
    public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByNameRole(String nameRole);
}





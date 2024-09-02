package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.kata.spring.boot_security.demo.entetie.Role;
import ru.kata.spring.boot_security.demo.reposotorie.RoleRepository;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	private final RoleRepository roleRepository;

	public SpringBootSecurityDemoApplication(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	public List<Role> getRole(String nameRole) {
		List<Role> roles = new ArrayList<>();
		roles.add(roleRepository.findByNameRole(nameRole));
		return roles;

	}
}
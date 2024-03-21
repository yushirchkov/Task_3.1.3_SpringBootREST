package ru.itmentor.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itmentor.spring.boot_security.demo.model.Role;
import ru.itmentor.spring.boot_security.demo.model.User;
import ru.itmentor.spring.boot_security.demo.service.RoleService;
import ru.itmentor.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

	private final RoleService roleService;
	private final UserService userService;

	@Autowired
	public SpringBootSecurityDemoApplication(RoleService roleService, UserService userService) {
		this.roleService = roleService;
		this.userService = userService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
	}

	@PostConstruct
	public void createRolesAndUser() {
		Role adminRole = new Role();
		adminRole.setRole("ROLE_ADMIN");
		roleService.createRole(adminRole);

		Role userRole = new Role();
		userRole.setRole("ROLE_USER");
		roleService.createRole(userRole);

		User user1 = new User();
		user1.setName("Anna");
		user1.setLastName("Kot");
		user1.setEmail("kot@gmail.com");
		user1.setJobPosition("admin");
		user1.setUsername("annak");
		user1.setPassword("123");

		Set<Role> userRoles = new HashSet<>();
		userRoles.add(adminRole);
		user1.setRoles(userRoles);
		user1.addRole(adminRole);
		userService.createUser(user1);

	}

}


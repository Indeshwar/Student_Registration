package com.web.student_register;

import com.web.student_register.Service.CustomUserService;
import com.web.student_register.entity.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class StudentRegisterApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentRegisterApplication.class, args);
	}

	@Autowired
	private CustomUserService customerService;
	@Override
	public void run(String... args) throws Exception {
		Permission readPermission = customerService.createPermission("READ_PERMISSION");
		Permission writePermission = customerService.createPermission("WRITE_PERMISSION");
		Permission updatePermission = customerService.createPermission("UPDATE_PERMISSION");
		Permission deletePermission = customerService.createPermission("DELETE_PERMISSION");

		Set<Permission> adminPermission = new HashSet<>();
		adminPermission.add(readPermission);
		adminPermission.add(updatePermission);
		adminPermission.add(writePermission);
		adminPermission.add(deletePermission);

		Set<Permission> userPermission = new HashSet<>();
		userPermission.add(readPermission);
		userPermission.add(updatePermission);


		customerService.createRole("ROLE_TEACHER", adminPermission);
		customerService.createRole("ROLE_STUDENT", userPermission);


	}
}

package com.learner;

import com.learner.config.AppConstants;
import com.learner.entities.Role;
import com.learner.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class BlogHubApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogHubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		System.out.println(this.passwordEncoder.encode("12345678"));
//		try {
//			Role role = new Role();
//			role.setId(AppConstants.ADMIN_USER);
//			role.setName("USER_ADMIN");
//
//			Role role1 = new Role();
//			role.setId(AppConstants.NORMAL_USER);
//			role.setName("USER_NORMAL");
//
//			List<Role> roles = List.of(role, role1);
//			List<Role> result = roleRepo.saveAll(roles);
//			result.forEach(r-> System.out.println(r.getName()));
//		}catch (Exception err){
//			System.out.println(err.getMessage());
//		}
	}
}

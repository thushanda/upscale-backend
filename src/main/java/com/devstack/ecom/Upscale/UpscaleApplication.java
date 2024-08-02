package com.devstack.ecom.Upscale;

import com.devstack.ecom.Upscale.service.UserRoleService;
import com.devstack.ecom.Upscale.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UpscaleApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(UpscaleApplication.class, args);
	}

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Override
	public void run(String... args) throws Exception {
		userRoleService.initializerUserRoles();
		userService.initializeAdmin();
	}
}

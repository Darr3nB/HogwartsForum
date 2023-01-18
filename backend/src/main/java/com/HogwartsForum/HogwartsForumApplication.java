package com.HogwartsForum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HogwartsForumApplication {

	public static void main(String[] args) {
		// TODO Learn logger and use them instead of sout the error
		// TODO Usage of JWTs
		// TODO e-mail when register and send automatic a mail
		// TODO build the reputation system
		// TODO picture handle, upload from frontend, save it
		// TODO put a default admin role in db when initialized
		// TODO put a 'data.sql' file in 'resources' folder for adding a default admin user
		SpringApplication.run(HogwartsForumApplication.class, args);
	}
}

package com.demo.ngsoft;


import com.demo.ngsoft.entities.User;
import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NgsoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgsoftApplication.class, args);
	}

/*	@Bean
	CommandLineRunner commandLineRunner(AuthenticationService service, UserRepo repo){
		return args -> {
			if(repo.findByEmail("orenv3@gmail").isEmpty()) {
				CreateUserRequest createAdmin = new CreateUserRequest("oren", "orenv3@gmail", true, true, "1234");
				System.out.println(service.registerUser(createAdmin));

			}
		};
	}*/

}



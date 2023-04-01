package com.demo.ngsoft;


import com.demo.ngsoft.repositories.UserRepo;
import com.demo.ngsoft.requestObjects.CreateUserRequest;
import com.demo.ngsoft.security.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NgsoftApplication {

	public static void main(String[] args) {
		SpringApplication.run(NgsoftApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(AuthenticationService service, UserRepo repo){
		return args -> {

				CreateUserRequest createAdmin = new CreateUserRequest("oren", "orenv@vinogura", true, true, "1234");
				System.out.println(service.registerUser(createAdmin));


		};
	}

}



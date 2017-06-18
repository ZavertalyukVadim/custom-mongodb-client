package com;

import com.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMongoApplication implements CommandLineRunner {
	private final EntityService entityService;

	@Autowired
	public ClientMongoApplication(EntityService entityService) {
		this.entityService = entityService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ClientMongoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
//		entityService.getAllEntity().forEach(System.out::print);
		System.out.println(entityService.getAllEntity());
		System.out.println("HW!!!");
	}
}

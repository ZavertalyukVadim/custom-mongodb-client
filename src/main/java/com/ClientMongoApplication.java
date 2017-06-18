package com;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientMongoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ClientMongoApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		System.out.println("HW!!!");
	}
}

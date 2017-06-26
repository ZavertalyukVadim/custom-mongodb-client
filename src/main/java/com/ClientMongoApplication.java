package com;

import com.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
        menu();
    }

    private void menu() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while (true) {
            System.out.print("write = ");
            s = bufferedReader.readLine();
            switch (s) {
                case "stop":
                    return;
                case "view":
                    System.out.println(entityService.getAllEntity());
                    break;
                default:
                    System.out.println("write correct keyword");
            }
        }
    }
}

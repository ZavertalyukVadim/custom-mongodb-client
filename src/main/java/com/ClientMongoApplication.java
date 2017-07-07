package com;

import com.service.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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
        menu(strings);
    }

    private void menu(String[] strings) throws IOException {
        System.out.println(Arrays.toString(strings));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String query;
        while (true) {
            System.out.print("write = ");
            query = bufferedReader.readLine();
            switch (query) {
                case "stop":
                    return;
                case "*":
                    entityService.getAllEntity(query);
                    break;
                case "age":
                    entityService.getField(query);
                    break;
                default:
                    System.out.println("write correct keyword");
            }
        }
    }
}

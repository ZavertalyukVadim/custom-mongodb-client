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
        String query;
        while (true) {
            System.out.print("write = ");
            query = bufferedReader.readLine();
            switch (query) {
                case "*":
                    entityService.getAllFieldsFromEntity(query);
                    break;
                case "sex":
                    entityService.getFieldSex(query);
                    break;
                case "name":
                    entityService.getFieldName(query);
                    break;
                case "entity.*":
                    entityService.getAllFieldsFromSubField(query);
                    break;
                case "object.firstName":
                    entityService.getSubFieldFirstName(query);
                    break;
                case "object.lastName":
                    entityService.getSubFieldLastName(query);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("write correct keyword");
            }
        }
    }
}

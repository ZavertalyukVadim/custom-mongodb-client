package com;

import com.dto.SqlDto;
import com.parser.Parser;
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
        String exampleSql = "SELECT * " +
                "FROM entity " +
                "WHERE entity.name = `lol` " +
                "GROUP BY entity.name, entity.sex, entity.object " +
                "ORDER BY category.name ASC " +
                "LIMIT 3 " +
                "OFFSET 0";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String query;
        while (true) {
            System.out.print("Write = ");
            query = bufferedReader.readLine();
            Parser parser =  new Parser();
            SqlDto sqlDto = parser.parse(query);
            switch (sqlDto.getProjections()) {
                case "*":
                    entityService.getAllFieldsFromEntity(sqlDto);
                    break;
                case "sex":
                    entityService.getFieldSex(sqlDto);
                    break;
                case "name":
                    entityService.getFieldName(sqlDto);
                    break;
                case "entity.*":
                    entityService.getAllFieldsFromSubField(sqlDto);
                    break;
                case "object.firstName":
                    entityService.getSubFieldFirstName(sqlDto);
                    break;
                case "object.lastName":
                    entityService.getSubFieldLastName(sqlDto);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Write correct query");
            }
        }
    }


    String exampleSql = "SELECT * FROM entity WHERE name = `lol` GROUP BY name, sex, object ORDER BY name ASC LIMIT 3 OFFSET 0";
}

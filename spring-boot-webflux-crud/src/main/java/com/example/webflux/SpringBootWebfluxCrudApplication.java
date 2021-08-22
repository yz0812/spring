package com.example.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class SpringBootWebfluxCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebfluxCrudApplication.class, args);
    }

}

package com.yz.flowable;

import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author yz
 */
@SpringBootApplication
public class SpringBootFlowableApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootFlowableApplication.class, args);
    }
}

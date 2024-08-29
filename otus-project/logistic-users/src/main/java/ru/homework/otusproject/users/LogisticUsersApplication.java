package ru.homework.otusproject.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"ru.homework.otusproject"})

public class LogisticUsersApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticUsersApplication.class, args);
    }

}

package ru.homework.otusproject.logisticserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LogisticServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticServerApplication.class, args);
    }

}

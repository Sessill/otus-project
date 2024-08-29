package ru.homework.otusproject.logisticgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class LogisticGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticGatewayApplication.class, args);
    }

}

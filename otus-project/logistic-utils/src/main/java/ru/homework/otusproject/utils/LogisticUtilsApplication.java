package ru.homework.otusproject.utils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"ru.homework.otusproject"})
public class LogisticUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(LogisticUtilsApplication.class, args);
    }

}

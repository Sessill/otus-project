package ru.homework.otusproject.logisticclaim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan(basePackages = {"ru.homework.otusproject"})
@EnableJpaRepositories(basePackages = {"ru.homework.otusproject"})
public class LogisticClaimApplication {

	public static void main(String[] args) {
		SpringApplication.run(LogisticClaimApplication.class, args);
	}

}

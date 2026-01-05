package com.fitness.activityservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
//@EnableMongoRepositories(basePackages = "com.fitness.activityservice.repository")
public class ActivityserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ActivityserviceApplication.class, args);
	}
}

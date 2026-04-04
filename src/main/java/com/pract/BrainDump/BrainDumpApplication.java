package com.pract.BrainDump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class BrainDumpApplication {
	public static void main(String[] args) {
		SpringApplication.run(BrainDumpApplication.class, args);
	}
}


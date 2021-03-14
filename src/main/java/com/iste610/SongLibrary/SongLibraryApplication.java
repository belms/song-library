package com.iste610.SongLibrary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.iste610.SongLibrary.repository")
public class SongLibraryApplication {
	public static void main(String[] args) {
		SpringApplication.run(SongLibraryApplication.class, args);
	}

}

package com.iste610.SongLibrary;

import com.iste610.SongLibrary.repository.SongRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = SongRepository.class)
public class SongLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(SongLibraryApplication.class, args);
	}

}

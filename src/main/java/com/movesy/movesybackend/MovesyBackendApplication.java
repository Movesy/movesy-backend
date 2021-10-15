package com.movesy.movesybackend;

import com.movesy.movesybackend.repository.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MovesyBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovesyBackendApplication.class, args);
    }
}

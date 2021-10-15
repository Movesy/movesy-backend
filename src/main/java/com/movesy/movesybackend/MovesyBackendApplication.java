package com.movesy.movesybackend;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.movesy.movesybackend.repository.UserRepository;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoDatabaseUtils;
import org.springframework.data.mongodb.core.MongoDatabaseFactorySupport;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
public class MovesyBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovesyBackendApplication.class, args);
    }
}

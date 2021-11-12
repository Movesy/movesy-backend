package com.movesy.movesybackend.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

@Configuration
public class MongoDbConfig extends AbstractMongoClientConfiguration {
    @Override
    public MongoClient mongoClient() {
        System.setProperty("jasypt.encryptor.password", "movesydb");

        ConnectionString connectionString = new ConnectionString("mongodb+srv://movesyAdmin:asdasdasd@movesy.gjupo.mongodb.net/movesy");
        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();

        return MongoClients.create(mongoClientSettings);
    }

    @Override
    protected String getDatabaseName() {
        return "movesy";
    }
}
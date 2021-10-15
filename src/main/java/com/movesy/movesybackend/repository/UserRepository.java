package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
}

package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends MongoRepository<Package, String> {

}

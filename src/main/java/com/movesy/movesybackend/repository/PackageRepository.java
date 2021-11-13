package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.Package;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackageRepository extends MongoRepository<Package, String> {
    List<Package> findPackageByUserID(String userID);
    List<Package> findPackageByTransporterID(String transporterID);
    Package findPackageById(String id);
}

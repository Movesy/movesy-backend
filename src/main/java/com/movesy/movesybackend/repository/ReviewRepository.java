package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.Package;
import com.movesy.movesybackend.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    Optional<Review> findReviewByPackageId(String id);
}

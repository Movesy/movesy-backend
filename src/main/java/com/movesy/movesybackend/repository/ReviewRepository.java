package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findReviewByPackageID(String id);

    List<Review> findReviewsByTransporterID(String id);
}

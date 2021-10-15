package com.movesy.movesybackend.repository;

import com.movesy.movesybackend.model.Offer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OfferRepository extends MongoRepository<Offer, String> {
    List<Offer> findOfferByPackageID(String id);
}

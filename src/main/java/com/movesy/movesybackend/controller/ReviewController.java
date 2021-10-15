package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.model.Review;
import com.movesy.movesybackend.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review,@RequestParam String Username,@RequestParam String PackageId,@RequestParam String TransporterId){
        try {
            Calendar actual_time = Calendar.getInstance();
            review.setTransporterID(TransporterId);
            review.setCustomerUsername(Username);
            review.setPackageID(PackageId);
            review.setTime(actual_time.getTime());
            reviewRepository.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("INTERNAL_SERVER_ERROR!");
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getReviewByPackageId(@RequestParam String id){
        try{
            Optional<Review> review = reviewRepository.findReviewsByPackageID(id);
            if (review.isEmpty()) {
                return new ResponseEntity<Review>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("edit/")
    public ResponseEntity<?> editReviewByPackageId(@RequestParam String id,@RequestBody Review review){
        Optional<Review> reviewData = reviewRepository.findReviewsByPackageID(id);
        if (reviewData.isPresent()) {
            Review _review = reviewData.get();
            _review.setDescription(review.getDescription());
            _review.setRating(review.getRating());
            return new ResponseEntity<>(reviewRepository.save(_review),HttpStatus.OK);
        } else {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

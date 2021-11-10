package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.config.JwtTokenUtil;
import com.movesy.movesybackend.model.Review;
import com.movesy.movesybackend.repository.ReviewRepository;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        try {
            String token = JwtTokenUtil.getToken();
            review.setTransporterID(jwtTokenUtil.getUserFromToken(token).getId());
            Calendar actual_time = Calendar.getInstance();
            review.setTime(actual_time.getTime());
            reviewRepository.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        } catch (Exception e) {
            LogFactory.getLog(this.getClass()).error("INTERNAL_SERVER_ERROR!");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Review> getReviewByPackageId(@RequestParam String id) {
        try {
            List<Review> reviews = reviewRepository.findReviewByPackageID(id);

            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews.get(0), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/")
    public ResponseEntity<?> editReviewById(@RequestBody Review editedReview) {
        Optional<Review> reviewData = reviewRepository.findById(editedReview.getId());
        if (reviewData.isPresent()) {
            Review _review = reviewData.get();
            editedReview.setId(_review.getId());
            _review = editedReview;
            return new ResponseEntity<>(reviewRepository.save(_review), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteReview(@RequestParam String id) {
        try {
            reviewRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/transporter/")
    public ResponseEntity<List<Review>> getReviewsByTransporter(@RequestParam String id) {
        try {
            List<Review> reviews = reviewRepository.findReviewsByTransporterID(id);

            if (reviews.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

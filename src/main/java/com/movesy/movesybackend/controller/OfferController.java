package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.model.Offer;
import com.movesy.movesybackend.model.Package;
import com.movesy.movesybackend.model.Role;
import com.movesy.movesybackend.model.User;
import com.movesy.movesybackend.repository.OfferRepository;
import com.movesy.movesybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("/create/")
    public ResponseEntity<Offer> createOffer(@RequestBody Offer offer, @RequestParam String transporterID, @RequestParam String packageID) {
        try {
            offer.setTransporterID(transporterID);
            offer.setPackageID(packageID);
            offerRepository.save(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("INTERNAL_SERVER_ERROR!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Offer>> getOffersByPackageID(@RequestParam String id) {
        try {
            List<Offer> offers = offerRepository.findOfferByPackageID(id);

            if (offers.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(offers, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/edit/")
    public ResponseEntity<Offer> updatePackage(@RequestParam String id, @RequestBody Package editedOffer, @RequestParam String userID) {
        Optional<Offer> offerData = offerRepository.findById(id);
        Optional<User> userData = userRepository.findById(userID);

        if (offerData.isPresent() && userData.isPresent() && offerData.get().getTransporterID().equals(userID)) {
            Offer _offer = offerData.get();
            _offer.setPrice(editedOffer.getPrice());
            return new ResponseEntity<>(offerRepository.save(_offer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteOffer(@RequestParam String id) {
        try {
            offerRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

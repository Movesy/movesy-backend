package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.config.JwtTokenUtil;
import com.movesy.movesybackend.model.*;
import com.movesy.movesybackend.model.Package;
import com.movesy.movesybackend.repository.OfferRepository;
import com.movesy.movesybackend.repository.PackageRepository;
import com.movesy.movesybackend.repository.UserRepository;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.web.bind.annotation.*;

import javax.management.InstanceNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/offer")
public class OfferController {

    @Autowired
    OfferRepository offerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create")
    public ResponseEntity<Offer> createOffer(@Valid @RequestBody Offer offer) {
        try {
            String token = JwtTokenUtil.getToken();
            offer.setTransporterID(jwtTokenUtil.getUserFromToken(token).getId());
            offerRepository.save(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } catch (Exception e) {
            LogFactory.getLog(this.getClass()).error("INTERNAL_SERVER_ERROR!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/transfer")
    public ResponseEntity<Offer> transferPackage(@RequestParam String offerID, @RequestParam String newTransporterID) {
        try {
            String token = JwtTokenUtil.getToken();
            Offer offer = offerRepository.findOfferById(offerID);
            if (offer == null)
                throw new InstanceNotFoundException("There is no offer with this ID");
            if (!packageRepository.findPackageById(offer.getPackageID()).getStatus().equals(Status.SENT))
                throw new Exception("It is too late now to transfer this offer to another transporter");
            Optional<User> oldTransporter = userRepository.findById(offer.getTransporterID());
            if (oldTransporter.isPresent()) {
                User currentUser = jwtTokenUtil.getUserFromToken(token);
                if (!oldTransporter.get().getId().equals(currentUser.getId()) && currentUser.getRole().equals(Role.ADMIN))
                    throw new AuthorizationServiceException("You dont have permission to make changes");
            }
            offer.setTransporterID(newTransporterID);
            offerRepository.save(offer);
            return new ResponseEntity<>(offer, HttpStatus.OK);
        } catch (Exception e) {
            LogFactory.getLog(this.getClass()).error("INTERNAL_SERVER_ERROR!");
            LogFactory.getLog(this.getClass()).error(e.getMessage());
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
    public ResponseEntity<Offer> updateOffer(@Valid @RequestBody Offer editedOffer) {
        Optional<Offer> offerData = offerRepository.findById(editedOffer.getId());
        String token = JwtTokenUtil.getToken();
        User user = jwtTokenUtil.getUserFromToken(token);
        if (offerData.isPresent() && (Objects.equals(user.getId(), editedOffer.getTransporterID()) || user.getRole() == Role.ADMIN)) {
            return new ResponseEntity<>(offerRepository.save(editedOffer), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/accept/")
    public ResponseEntity<HttpStatus> acceptOffer(@RequestParam String id) {
        try {
            Optional<Offer> offerData = offerRepository.findById(id);
            if (offerData.isPresent()) {
                Package _package = packageRepository.findPackageById(offerData.get().getPackageID());
                String token = JwtTokenUtil.getToken();
                User user = jwtTokenUtil.getUserFromToken(token);
                if (!Objects.equals(_package.getUserID(), user.getId()))
                    throw new SecurityException("This user does not have the right to accept this offer!");
                List<Offer> offers = offerRepository.findOfferByPackageID(offerData.get().getPackageID());
                for (Offer offer : offers) {
                    offerRepository.deleteById(offer.getId());
                }
                _package.setTransporterID(offerData.get().getTransporterID());
                _package.setPrice(offerData.get().getPrice());
                _package.setStatus(Status.SENT);
                packageRepository.save(_package);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/reject/")
    public ResponseEntity<HttpStatus> rejectOffer(@RequestParam String id) {
        return deleteOffer(id);
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteOffer(@RequestParam String id) {
        try {
            Optional<Offer> offerData = offerRepository.findById(id);
            if (offerData.isPresent()) {
                Package _package = packageRepository.findPackageById(offerData.get().getPackageID());
                String token = JwtTokenUtil.getToken();
                User user = jwtTokenUtil.getUserFromToken(token);
                if (!Objects.equals(_package.getUserID(), user.getId()))
                    throw new SecurityException("This user does not have the right to modify this offer!");
                offerRepository.deleteById(id);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

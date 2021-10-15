package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.model.Package;
import com.movesy.movesybackend.repository.PackageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    PackageRepository packageRepository;

    @PostMapping("/create")
    public ResponseEntity<Package> createPackage(@RequestBody Package _package, @RequestParam String userID) {
        try {
            _package.setUserID(userID);
            packageRepository.save(_package);
            return new ResponseEntity<>(_package, HttpStatus.OK);
        } catch (Exception e) {
            System.out.println("INTERNAL_SERVER_ERROR!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Package>> getAllPackages() {
        try {
            List<Package> packages = new ArrayList<>(packageRepository.findAll());

            if (packages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<Package> getPackageById(@RequestParam String id) {
        Optional<Package> packageData = packageRepository.findById(id);

        return packageData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit/")
    public ResponseEntity<Package> updatePackage(@RequestParam String id, @RequestBody Package editedPackage) {
        Optional<Package> packageData = packageRepository.findById(id);

        if (packageData.isPresent()) {
            Package _package = packageData.get();
            _package.setFrom(editedPackage.getFrom());
            _package.setTo(editedPackage.getTo());
            _package.setDeadline(editedPackage.getDeadline());
            _package.setPrice(editedPackage.getPrice());
            _package.setSize(editedPackage.getSize());
            _package.setWeight(editedPackage.getWeight());
            return new ResponseEntity<>(packageRepository.save(_package), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deletePackage(@RequestParam String id) {
        try {
            packageRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/")
    public ResponseEntity<List<Package>> getPackagesByUser(@RequestParam String id) {
        try {
            List<Package> packages = packageRepository.findPackageByUserID(id);

            if (packages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}

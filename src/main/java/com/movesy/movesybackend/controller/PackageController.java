package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.config.JwtTokenUtil;
import com.movesy.movesybackend.model.Package;
import com.movesy.movesybackend.model.Role;
import com.movesy.movesybackend.model.User;
import com.movesy.movesybackend.repository.PackageRepository;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    PackageRepository packageRepository;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/create")
    public ResponseEntity<Package> createPackage(@Valid @RequestBody Package _package) {
        String token = JwtTokenUtil.getToken();
        try {
            _package.setUserID(jwtTokenUtil.getUserFromToken(token).getId());
            packageRepository.save(_package);
            return new ResponseEntity<>(_package, HttpStatus.OK);
        } catch (Exception e) {
            LogFactory.getLog(this.getClass()).error("INTERNAL_SERVER_ERROR!");
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
        return packageData.map(pack -> new ResponseEntity<>(pack, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit/")
    public ResponseEntity<Package> updatePackage(@Valid @RequestBody Package editedPackage) {
        Optional<Package> packageData = packageRepository.findById(editedPackage.getId());
        String token = JwtTokenUtil.getToken();
        User user = jwtTokenUtil.getUserFromToken(token);
        if (packageData.isPresent()
                && (user.getId().equals(editedPackage.getUserID())
                || user.getId().equals(editedPackage.getTransporterID())
                || user.getRole() == Role.ADMIN)) {
            return new ResponseEntity<>(packageRepository.save(editedPackage), HttpStatus.OK);
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

    @GetMapping("/transporter/")
    public ResponseEntity<List<Package>> getPackagesByTransporter(@RequestParam String id) {
        try {
            List<Package> packages = packageRepository.findPackageByTransporterID(id);

            if (packages.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(packages, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

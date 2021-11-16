package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.config.JwtTokenUtil;
import com.movesy.movesybackend.model.Role;
import com.movesy.movesybackend.model.User;
import com.movesy.movesybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @GetMapping("/list")
    public ResponseEntity<?> getAllUsers() {
        try {
            List<User> users = new ArrayList<>(userRepository.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No users available", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    public ResponseEntity<User> getUserById(@RequestParam String id) {
        Optional<User> userData = userRepository.findById(id);
        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/edit/")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User editedUser) {
        Optional<User> userData = userRepository.findById(editedUser.getId());
        String token = JwtTokenUtil.getToken();
        User user = jwtTokenUtil.getUserFromToken(token);
        if (userData.isPresent() && (Objects.equals(user.getId(), editedUser.getId()) || user.getRole() == Role.ADMIN)) {
            return new ResponseEntity<>(userRepository.save(editedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam String id) {
        try {
            userRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

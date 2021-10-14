package com.movesy.movesybackend.controller;

import com.movesy.movesybackend.model.Role;
import com.movesy.movesybackend.model.Size;
import com.movesy.movesybackend.model.User;
import com.movesy.movesybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser() {
        userRepository.save(new User("123", Role.ADMIN, "Tomi", "jelszo", "email@email.com", "+36201234567", Size.HUGE));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

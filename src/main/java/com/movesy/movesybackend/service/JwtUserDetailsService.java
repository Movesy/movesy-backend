package com.movesy.movesybackend.service;

import java.util.List;

import com.movesy.movesybackend.model.Role;
import com.movesy.movesybackend.model.User;
import com.movesy.movesybackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        Role userRole = user.getRole();
        String roleName = userRole == Role.ADMIN ? "ROLE_ADMIN" : userRole == Role.USER ? "ROLE_USER" : userRole == Role.TRANSPORTER ? "ROLE_TRANSPORTER" : "";

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(roleName);
        List<SimpleGrantedAuthority> authorities = List.of(simpleGrantedAuthority);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User save(User user) {
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
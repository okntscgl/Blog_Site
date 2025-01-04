package com.okntscgl.Blog.service;

import com.okntscgl.Blog.model.User;
import com.okntscgl.Blog.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<String> login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUserName(username);

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("User not found");
        }

        User user = optionalUser.get();

        if (passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        }

        return ResponseEntity.status(401).body("Invalid credentials");
    }

    public ResponseEntity<String> register(User user) {
        if (userRepository.existsByUserName(user.getUserName())) {
            return ResponseEntity.status(400).body("Username already exists");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(400).body("Email already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        return ResponseEntity.status(201).body("User registered successfully");
    }

    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logged out successfully");
    }
}

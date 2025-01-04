package com.okntscgl.Blog.controller;

import com.okntscgl.Blog.model.User;
import com.okntscgl.Blog.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
        return authService.login(username, password);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return authService.logout();
    }
}

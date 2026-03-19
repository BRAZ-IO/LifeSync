package com.lifesync.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    
    @PostMapping("/register")
    public ResponseEntity<?> register() {
        // TODO: Implement user registration
        return ResponseEntity.ok("Registration endpoint");
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login() {
        // TODO: Implement user login
        return ResponseEntity.ok("Login endpoint");
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<?> refresh() {
        // TODO: Implement token refresh
        return ResponseEntity.ok("Token refresh endpoint");
    }
    
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // TODO: Implement user logout
        return ResponseEntity.ok("Logout endpoint");
    }
}

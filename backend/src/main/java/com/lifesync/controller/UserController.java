package com.lifesync.controller;

import com.lifesync.entity.User;
import com.lifesync.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*")
public class UserController {
    
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        // TODO: Implement get all users (admin only)
        return ResponseEntity.ok(List.of());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // TODO: Implement get user by id
        return ResponseEntity.ok(new User());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        // TODO: Implement user update
        return ResponseEntity.ok(new User());
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // TODO: Implement user deletion
        return ResponseEntity.ok().build();
    }
    
    @GetMapping("/profile")
    public ResponseEntity<User> getUserProfile() {
        // TODO: Implement get current user profile
        return ResponseEntity.ok(new User());
    }
}

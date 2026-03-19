package com.lifesync.controller;

import com.lifesync.dto.AuthResponseDTO;
import com.lifesync.dto.LoginDTO;
import com.lifesync.dto.UserDTO;
import com.lifesync.entity.User;
import com.lifesync.service.UserService;
import com.lifesync.utility.BackendException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody UserDTO userDTO) {
        try {
            User user = userService.createUser(userDTO);
            
            // Convert User to UserDTO (without password)
            UserDTO responseUserDTO = new UserDTO();
            responseUserDTO.setUsername(user.getUsername());
            responseUserDTO.setEmail(user.getEmail());
            
            AuthResponseDTO response = new AuthResponseDTO(
                "User registered successfully", 
                "temp-token-" + user.getId(), // Temporary token for development
                responseUserDTO
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (BackendException e) {
            AuthResponseDTO response = new AuthResponseDTO(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            // Find user by email
            User user = userService.getUserByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new BackendException("Invalid email or password"));
            
            // Check password (in real app, this would be encrypted)
            if (!user.getPassword().equals(loginDTO.getPassword())) {
                throw new BackendException("Invalid email or password");
            }
            
            // Convert User to UserDTO (without password)
            UserDTO responseUserDTO = new UserDTO();
            responseUserDTO.setUsername(user.getUsername());
            responseUserDTO.setEmail(user.getEmail());
            
            AuthResponseDTO response = new AuthResponseDTO(
                "Login successful", 
                "temp-token-" + user.getId(), // Temporary token for development
                responseUserDTO
            );
            
            return ResponseEntity.ok(response);
        } catch (BackendException e) {
            AuthResponseDTO response = new AuthResponseDTO(e.getMessage(), null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponseDTO> refresh(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("temp-token-")) {
            AuthResponseDTO response = new AuthResponseDTO("Invalid token", null);
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            // Extract user ID from temporary token
            Long userId = Long.parseLong(token.substring("temp-token-".length()));
            
            User user = userService.getUserById(userId)
                .orElseThrow(() -> new BackendException("User not found"));
            
            // Convert User to UserDTO (without password)
            UserDTO responseUserDTO = new UserDTO();
            responseUserDTO.setUsername(user.getUsername());
            responseUserDTO.setEmail(user.getEmail());
            
            AuthResponseDTO response = new AuthResponseDTO(
                "Token refreshed", 
                "temp-token-" + user.getId(), // New temporary token
                responseUserDTO
            );
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            AuthResponseDTO response = new AuthResponseDTO("Invalid token", null);
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Logout successful");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/validate")
    public ResponseEntity<Map<String, Object>> validateToken(@RequestHeader(value = "Authorization", required = false) String token) {
        Map<String, Object> response = new HashMap<>();
        
        if (token == null || !token.startsWith("temp-token-")) {
            response.put("valid", false);
            response.put("message", "Invalid token");
            return ResponseEntity.badRequest().body(response);
        }
        
        try {
            // Extract user ID from temporary token
            Long userId = Long.parseLong(token.substring("temp-token-".length()));
            
            User user = userService.getUserById(userId)
                .orElseThrow(() -> new BackendException("User not found"));
            
            response.put("valid", true);
            response.put("userId", user.getId());
            response.put("username", user.getUsername());
            response.put("email", user.getEmail());
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("valid", false);
            response.put("message", "Invalid token");
            return ResponseEntity.badRequest().body(response);
        }
    }
}

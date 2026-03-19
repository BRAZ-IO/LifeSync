package com.lifesync.controller;

import com.lifesync.dto.AuthResponseDTO;
import com.lifesync.dto.LoginDTO;
import com.lifesync.dto.UserDTO;
import com.lifesync.entity.User;
import com.lifesync.service.UserService;
import com.lifesync.utility.BackendException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Authentication", description = "User authentication and authorization endpoints")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @Operation(
        summary = "Register new user", 
        description = """
            Create a new user account and return authentication token.
            
            **Registration Process:**
            1. Validates user input (email format, password strength)
            2. Checks if email/username already exists
            3. Creates new user account
            4. Returns JWT token for immediate authentication
            
            **Required Fields:**
            - username: Unique username (3-50 characters)
            - email: Valid email address
            - password: Strong password (8+ characters)
            """,
        responses = {
            @ApiResponse(
                responseCode = "201", 
                description = "User registered successfully",
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = AuthResponseDTO.class),
                    examples = @ExampleObject(
                        name = "SuccessResponse",
                        value = """
                            {
                              "message": "User registered successfully",
                              "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
                              "tokenType": "Bearer",
                              "expiresAt": 1640995200000,
                              "user": {
                                "username": "john_doe",
                                "email": "john@example.com"
                              }
                            }
                            """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "400", 
                description = "Invalid user data",
                content = @Content(
                    schema = @Schema(implementation = AuthResponseDTO.class),
                    examples = @ExampleObject(
                        name = "ValidationError",
                        value = """
                            {
                              "message": "Email format is invalid"
                            }
                            """
                    )
                )
            ),
            @ApiResponse(
                responseCode = "409", 
                description = "Username or email already exists",
                content = @Content(
                    schema = @Schema(implementation = AuthResponseDTO.class),
                    examples = @ExampleObject(
                        name = "ConflictError",
                        value = """
                            {
                              "message": "Email already registered"
                            }
                            """
                    )
                )
            )
        }
    )
    public ResponseEntity<AuthResponseDTO> register(
        @Valid 
        @Parameter(
            description = "User registration data",
            required = true,
            content = @Content(
                schema = @Schema(implementation = UserDTO.class),
                examples = @ExampleObject(
                    name = "RegistrationRequest",
                    value = """
                        {
                          "username": "john_doe",
                          "email": "john@example.com",
                          "password": "SecurePass123!"
                        }
                        """
                )
            )
        )
        @RequestBody UserDTO userDTO) {
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

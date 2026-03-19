package com.lifesync.dto;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Data Transfer Object for Authentication Response
 * 
 * This DTO encapsulates the response structure for authentication operations,
 * including user registration and login responses.
 */
@Schema(
    name = "AuthResponse",
    description = "Authentication response containing token and user information"
)
public class AuthResponseDTO {
    
    @Schema(
        description = "Response message indicating the operation result",
        example = "Login successful",
        required = true
    )
    private String message;
    
    @Schema(
        description = "JWT authentication token for subsequent requests",
        example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c",
        required = false,
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String token;
    
    @Schema(
        description = "User information (excluding sensitive data)",
        required = false
    )
    private UserDTO user;
    
    @Schema(
        description = "Token expiration timestamp in milliseconds",
        example = "1640995200000",
        required = false,
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private Long expiresAt;
    
    @Schema(
        description = "Token type (always Bearer for JWT)",
        example = "Bearer",
        required = false,
        accessMode = Schema.AccessMode.READ_ONLY
    )
    private String tokenType;

    public AuthResponseDTO() {
        this.tokenType = "Bearer";
    }

    public AuthResponseDTO(String message, UserDTO user) {
        this();
        this.message = message;
        this.user = user;
    }

    public AuthResponseDTO(String message, String token, UserDTO user) {
        this(message, user);
        this.token = token;
        if (token != null) {
            // Set expiration to 24 hours from now
            this.expiresAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
        if (token != null) {
            this.expiresAt = System.currentTimeMillis() + (24 * 60 * 60 * 1000);
        }
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public Long getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}

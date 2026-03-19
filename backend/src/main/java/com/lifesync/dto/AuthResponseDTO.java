package com.lifesync.dto;

public class AuthResponseDTO {
    private String message;
    private String token;
    private UserDTO user;

    public AuthResponseDTO() {
    }

    public AuthResponseDTO(String message, UserDTO user) {
        this.message = message;
        this.user = user;
    }

    public AuthResponseDTO(String message, String token, UserDTO user) {
        this.message = message;
        this.token = token;
        this.user = user;
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
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

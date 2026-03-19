package com.lifesync.service;

import com.lifesync.entity.User;
import com.lifesync.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    
    User createUser(UserDTO userDTO);
    
    Optional<User> getUserById(Long id);
    
    Optional<User> getUserByUsername(String username);
    
    Optional<User> getUserByEmail(String email);
    
    List<User> getAllUsers();
    
    User updateUser(Long id, UserDTO userDTO);
    
    void deleteUser(Long id);
    
    boolean existsByUsername(String username);
    
    boolean existsByEmail(String email);
}

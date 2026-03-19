package com.lifesync.config;

import com.lifesync.entity.User;
import com.lifesync.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        // Check if test user already exists
        if (!userRepository.existsByUsername("testuser")) {
            User testUser = new User();
            testUser.setUsername("testuser");
            testUser.setEmail("test@example.com");
            testUser.setPassword("password123"); // In real app, this would be encrypted
            
            userRepository.save(testUser);
            System.out.println("✅ Test user created: testuser (ID: 1)");
        } else {
            System.out.println("ℹ️ Test user already exists: testuser (ID: 1)");
        }
    }
}

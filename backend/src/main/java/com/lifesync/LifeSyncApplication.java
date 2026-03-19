package com.lifesync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class LifeSyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeSyncApplication.class, args);
    }
}

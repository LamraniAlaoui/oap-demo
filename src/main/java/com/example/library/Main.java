package com.example.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableCaching  // Enable caching
@EnableJpaAuditing  // Enable JPA Auditing for tracking entity changes
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

}

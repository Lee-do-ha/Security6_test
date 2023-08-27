package com.example.security6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Security6Application {

    public static void main(String[] args) {
        SpringApplication.run(Security6Application.class, args);
    }

}

package com.example.springbootadvanced;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootAdvancedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdvancedApplication.class, args);
    }

}

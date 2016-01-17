package com.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class LibraryCourseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryCourseProjectApplication.class, args);
    }
}
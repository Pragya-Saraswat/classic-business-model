package com.businessmodel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.businessmodel")  
public class ClassicBusinessModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClassicBusinessModelApplication.class, args);
    }
}
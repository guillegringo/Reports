package com.example.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@ComponentScan(basePackages = "com.example")
public class JobServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobServerApplication.class, args);
    }
}

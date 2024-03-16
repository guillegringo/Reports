package com.example.reportservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.smartcardio.CardPermission;

@SpringBootApplication(scanBasePackages = "com.example")
@EnableScheduling
public class ReportServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(ReportServiceApplication.class, args);
	}
}
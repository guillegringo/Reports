package com.example.report.controller;

import com.example.report.service.SubscriptionService;
import com.example.report.service.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleController {

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    private ScheduleService scheduleService;

    @Operation(summary = "Schedule daily reports generation for a specific institution",
            description = "Endpoint to schedule the generation of daily reports for a specific institution")
    @PostMapping("/reports/schedule")
    public ResponseEntity<String> scheduleDailyReports(
            @Parameter(description = "Institution ID for which to generate reports", required = true)
            @RequestParam("institutionId") String institutionId) {
        try {
            scheduleService.scheduleDailyTasks(institutionId);
            return new ResponseEntity<>("Daily reports scheduled successfully for institution ID: " + institutionId,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to schedule daily reports", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}


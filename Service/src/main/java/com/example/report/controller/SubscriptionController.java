package com.example.report.controller;

import com.example.report.exceptions.NotFoundException;
import com.example.report.model.SubscriptionDto;
import com.example.report.service.SubscriptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reports/subscriptions")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    @Operation(summary = "Create a new subscription", description = "Endpoint to create a new subscription")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<String> createSubscription(
            @Parameter(description = "Subscription object to be created", required = true)
            @Valid @RequestBody SubscriptionDto subscription) {
        subscriptionService.createSubscription(subscription);
        return new ResponseEntity<>("Subscription created successfully", HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all subscriptions", description = "Endpoint to retrieve all subscriptions")
    public ResponseEntity<List<SubscriptionDto>> getAllSubscriptions() {
        List<SubscriptionDto> subscriptions = subscriptionService.getAllSubscriptions();
        return new ResponseEntity<>(subscriptions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a subscription by ID", description = "Endpoint to retrieve a subscription by its ID")
    public ResponseEntity<SubscriptionDto> getSubscriptionById(
            @Parameter(description = "ID of the subscription to be retrieved", required = true)
            @PathVariable Long id) {
        Optional<SubscriptionDto> subscription = subscriptionService.getSubscriptionById(id);
        return subscription.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a subscription", description = "Endpoint to update a subscription")
    public ResponseEntity<String> updateSubscription(
            @Parameter(description = "ID of the subscription to be updated", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated subscription object", required = true)
            @Valid @RequestBody SubscriptionDto subscription) {
        try {
            subscriptionService.updateSubscription(id, subscription);
            return new ResponseEntity<>("Subscription updated successfully", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Subscription not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a subscription", description = "Endpoint to delete a subscription")
    public ResponseEntity<String> deleteSubscription(
            @Parameter(description = "ID of the subscription to be deleted", required = true)
            @PathVariable Long id) {
        try {
            subscriptionService.deleteSubscription(id);
            return new ResponseEntity<>("Subscription deleted successfully", HttpStatus.OK);
        } catch (NotFoundException e) {
            return new ResponseEntity<>("Subscription not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

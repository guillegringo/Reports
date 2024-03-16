package com.example.reportservice.service;

import com.example.reportservice.exceptions.NotFoundException;
import com.example.reportservice.model.SubscriptionDto;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    public void createSubscription(SubscriptionDto subscription);

    public List<SubscriptionDto> getAllSubscriptions();

    public Optional<SubscriptionDto> getSubscriptionById(Long id);

    public void updateSubscription(Long id, SubscriptionDto subscription) throws NotFoundException;

    public void deleteSubscription(Long id) throws NotFoundException;
}

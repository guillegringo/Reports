package com.example.report.service;

import com.example.report.exceptions.NotFoundException;
import com.example.report.model.SubscriptionDto;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {

    public void createSubscription(SubscriptionDto subscription);

    public List<SubscriptionDto> getAllSubscriptions();

    public Optional<SubscriptionDto> getSubscriptionById(Long id);

    public void updateSubscription(Long id, SubscriptionDto subscription) throws NotFoundException;

    public void deleteSubscription(Long id) throws NotFoundException;
}

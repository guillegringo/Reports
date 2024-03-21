package com.example.report.service.impl;

import com.example.report.entity.Subscription;
import com.example.report.exceptions.NotFoundException;
import com.example.report.mapper.SubscriptionMapper;
import com.example.report.model.SubscriptionDto;
import com.example.report.dao.SubscriptionRepository;
import com.example.report.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {


    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;

    public void createSubscription(SubscriptionDto subscription) {
        Subscription entity = subscriptionMapper.dtoToEntity(subscription);
        subscriptionRepository.save(entity);
    }

    public List<SubscriptionDto> getAllSubscriptions() {
        List<Subscription> entities = subscriptionRepository.findAll();
        return entities.stream()
                .map(subscriptionMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public Optional<SubscriptionDto> getSubscriptionById(Long id) {
        Optional<Subscription> entityOptional = subscriptionRepository.findById(id);
        return entityOptional.map(subscriptionMapper::entityToDto);
    }

    public void updateSubscription(Long id, SubscriptionDto subscription) throws NotFoundException {
        if (subscriptionRepository.existsById(id)) {
            subscription.setSubscriptionId(id);
            Subscription entity = subscriptionMapper.dtoToEntity(subscription);
            subscriptionRepository.save(entity);
        } else {
            throw new NotFoundException("Subscription not found");
        }
    }

    public void deleteSubscription(Long id) throws NotFoundException {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
        } else {
            throw new NotFoundException("Subscription not found");
        }
    }
}

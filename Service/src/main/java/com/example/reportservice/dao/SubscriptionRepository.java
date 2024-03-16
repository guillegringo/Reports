package com.example.reportservice.dao;

import com.example.reportservice.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s WHERE s.period = 'DAILY'")
    List<Subscription> findAllDailySubscriptions();

    @Query("SELECT s FROM Subscription s WHERE s.period = 'WEEKLY'")
    List<Subscription> findAllWeeklySubscriptions();

    @Query("SELECT s FROM Subscription s WHERE s.period = 'MONTHLY'")
    List<Subscription> findAllMonthlySubscriptions();

}


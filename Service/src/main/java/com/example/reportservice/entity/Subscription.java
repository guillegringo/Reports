package com.example.reportservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "report_subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subscriptionId;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "merchants_included")
    private String merchantsIncluded;

    @Column(name = "period")
    private String period;

    @Column(name = "language")
    private String language;
}


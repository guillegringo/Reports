package com.example.report.entity;

import com.example.report.model.FileFormat;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "report_subscription")
@Data
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "institution_id")
    private String institutionId;

    @Column(name = "status")
    private String status;

    @Column(name = "report_type")
    private String reportType;

    @Column(name = "file_format")
    private String format;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "merchants_included")
    private String merchantsIncluded;

    @Column(name = "period")
    private String period;

    @Column(name = "language")
    private String language;
}


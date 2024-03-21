package com.example.report.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "report_generated")
@Data
public class ReportGenerated {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "institution_id")
    private String institutionId;

    @Column(name = "format")
    private String format;

    @Column(name = "language")
    private String language;

    @Column(name = "filename")
    private String filename;

    @Column(name = "subscription_id")
    private Long subscriptionId;

    @Column(name = "merchant_id")
    private String merchantId;

    @Lob
    @Column(name = "file")
    private byte[] file;

    @Column(name = "report_date")
    private Date reportDate;


}


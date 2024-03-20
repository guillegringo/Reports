package com.example.report.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportParams {

    private Long subscriptionId;
    private ReportType reportType;
    private String merchantId;
    private List<String> merchantsIncluded;
    private String language;
    private FileFormat format;
    private Date from;
    private Date to;

}

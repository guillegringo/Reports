package com.example.report.model;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ReportParams {

    private Long subscriptionId;
    private String institutionId;
    private ReportType reportType;
    private Period period;
    private String merchantId;
    private List<String> merchantsIncluded;
    private String language;
    private FileFormat format;
    private Date from;
    private Date to;

}

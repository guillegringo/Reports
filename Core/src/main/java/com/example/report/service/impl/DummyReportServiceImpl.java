package com.example.report.service.impl;

import com.example.report.model.ReportParams;
import com.example.report.service.DummyReportService;
import org.springframework.stereotype.Component;

@Component
public class DummyReportServiceImpl implements DummyReportService {

    @Override
    public void generate(ReportParams reportParams) {

        System.out.println("Processing:" + reportParams.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Processed: " + reportParams.toString());

    }
}

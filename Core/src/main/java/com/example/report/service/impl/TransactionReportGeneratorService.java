package com.example.report.service.impl;

import com.example.report.model.ReportParams;
import com.example.report.service.ReportGeneratorService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("TRANSACTION")
public class TransactionReportGeneratorService implements ReportGeneratorService {
    @Override
    public void generate(ReportParams params) {
        System.out.println("Processing TransactionReport:" + params.toString());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Processed TransactionReport: " + params.toString());

    }
}

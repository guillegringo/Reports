package com.example.report.service.impl;

import com.example.report.model.ReportParams;
import com.example.report.service.ReportGeneratorService;
import lombok.RequiredArgsConstructor;
import org.jobrunr.JobRunrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Primary
@RequiredArgsConstructor
public class ReportGeneratorServiceImpl implements ReportGeneratorService {

    @Autowired
    private final Map<String, ReportGeneratorService> services;

    @Override
    public void generate(ReportParams params) {
        try{
            services.get(params.getReportType().name().toLowerCase()+"ReportGeneratorServiceImpl").generate(params);
        } catch (NullPointerException ex){
            //throwing this specific jobrunr exception is to tell jobrunr to doNotRetry this
            throw new JobRunrException("Implementation not found for ReportType "+ params.getReportType().name(), true);
        }

    }

}

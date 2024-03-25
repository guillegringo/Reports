package com.example.report.service.impl;

import com.example.report.service.ReportGeneratorService;
import com.example.report.mapper.ReportParamsMapper;
import com.example.report.model.ReportParams;

import com.example.report.util.PeriodUtils;
import com.example.report.dao.SubscriptionRepository;
import com.example.report.service.ScheduleService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Stream;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ReportParamsMapper reportParamsMapper;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private SubscriptionRepository subscriptionRepository;


    @Override
    public void scheduleDailyTasks(String institutionId) {

        Stream<ReportParams> paramsStream = subscriptionRepository.findDailySubscriptionsByInstitutionId(institutionId)
                .stream()
                .map(subscription -> {
                    ReportParams reportParams = reportParamsMapper.subscriptionToReportParams(subscription);
                    reportParams.setFrom(PeriodUtils.getStartOfToday());
                    reportParams.setTo(PeriodUtils.getEndOfDay());
                    return reportParams;
                });

        jobScheduler.<ReportGeneratorService, ReportParams>enqueue(paramsStream, ReportGeneratorService::generate);
    }
}

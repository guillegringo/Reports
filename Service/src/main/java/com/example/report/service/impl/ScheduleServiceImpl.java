package com.example.report.service.impl;

import com.example.report.service.ReportGeneratorService;
import com.example.report.entity.Subscription;
import com.example.report.mapper.ReportParamsMapper;
import com.example.report.model.ReportParams;
import com.example.report.util.PeriodUtils;
import com.example.report.dao.SubscriptionRepository;
import com.example.report.service.ScheduleService;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ReportParamsMapper reportParamsMapper;

    @Autowired
    private JobScheduler jobScheduler;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public void scheduleDailyTasks(String intitutionId) {

        List<Subscription> subcriptions = subscriptionRepository.findDailySubscriptionsByInstitutionId(intitutionId);

        subcriptions.forEach(item -> {
            ReportParams reportParams = reportParamsMapper.subscriptionToReportParams(item);
            reportParams.setFrom(PeriodUtils.getStartOfToday());
            reportParams.setTo(PeriodUtils.getEndOfDay());
            jobScheduler.<ReportGeneratorService>enqueue(reportGeneratorService -> reportGeneratorService.generate(reportParams));
        });

    }
}
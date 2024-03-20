package com.example.reportservice.service.impl;

import com.example.report.model.ReportParams;
import com.example.report.service.DummyReportService;
import com.example.report.util.PeriodUtils;
import com.example.reportservice.dao.SubscriptionRepository;
import com.example.reportservice.entity.Subscription;
import com.example.reportservice.mapper.ReportParamsMapper;
import com.example.reportservice.service.ScheduleService;
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
            jobScheduler.<DummyReportService>enqueue(dummyReportService -> dummyReportService.generate(reportParams));
        });

    }
}

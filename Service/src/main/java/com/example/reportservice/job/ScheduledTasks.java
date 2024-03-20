package com.example.reportservice.job;

import com.example.report.model.ReportParams;
import com.example.report.service.DummyReportService;
import com.example.report.util.PeriodUtils;
import com.example.reportservice.dao.SubscriptionRepository;
import com.example.reportservice.mapper.ReportParamsMapper;
import com.example.reportservice.mapper.SubscriptionMapper;
import com.example.reportservice.model.SubscriptionDto;
import lombok.RequiredArgsConstructor;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ScheduledTasks {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private SubscriptionMapper subscriptionMapper;


    @Autowired
    private ReportParamsMapper reportParamsMapper;

    private final DummyReportService dummyReportService;
    @Autowired
    private JobScheduler jobScheduler;

    // Ejecuta el método cada día a las 10:00 AM
 //   @Scheduled(cron = "0 0 10 * * ?")
    public void dailyTask() {
        List<SubscriptionDto> subscriptions = this.subscriptionRepository.findAllDailySubscriptions().stream().map(subscriptionMapper::entityToDto).toList();

/*        subscriptions.forEach(item -> {
            ReportParams reportParams = reportParamsMapper.subscriptionDtoToReportParams(item);
            reportParams.setFrom(PeriodUtils.getStartOfToday());
            reportParams.setTo(PeriodUtils.getEndOfDay());
            jobScheduler.<DummyReportService>enqueue(dummyReportService -> dummyReportService.generate(reportParams));
        });*/
    }


    // Ejecuta el método cada lunes a las 9:00 AM
  //  @Scheduled(cron = "0 0 9 ? * MON")
    public void weeklyTask() {
        System.out.println("Tarea semanal ejecutada.");
    }

    // Ejecuta el método el primer día de cada mes a las 8:00 AM
 //   @Scheduled(cron = "0 0 8 1 * ?")
    public void monthlyTask() {
        System.out.println("Tarea mensual ejecutada.");
    }

/*
    // PARA PROBAR
    @Scheduled(cron = "0 10 17 * * *")
    public void paraProbar() {
        this.dailyTask();
        ReportParams params = new ReportParams();
    }*/


    // PARA PROBAR
 //   @Scheduled(initialDelay = 10000, fixedRate = 10000000)
    public void scheduleTask() {
        this.dailyTask();
    }


}

package com.example.reportservice.mapper;
import com.example.report.model.Period;
import com.example.report.model.ReportType;
import com.example.reportservice.entity.Subscription;

import com.example.reportservice.model.SubscriptionDto;
import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;


@Mapper (componentModel = "spring")
public interface SubscriptionMapper {
    SubscriptionMapper INSTANCE = Mappers.getMapper(SubscriptionMapper.class);

    Subscription dtoToEntity(SubscriptionDto subscription);

    SubscriptionDto entityToDto(Subscription entity);

    default String map(List<String> value) {
        if (value == null || value.isEmpty()) {
            return "";
        }
        return String.join(",", value);
    }

    default List<String> map(String value) {
        if (value == null || value.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(value.split(","));
    }

    default String mapReportType(ReportType reportType) {
        return reportType.name();
    }

    default ReportType mapReportType(String reportType) {
        return ReportType.valueOf(reportType);
    }

    default String mapPeriod(Period period) {
        return period.name();
    }

    default Period mapPeriod(String period) {
        return Period.valueOf(period);
    }
}


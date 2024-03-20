package com.example.reportservice.mapper;

import com.example.report.model.FileFormat;
import com.example.report.model.ReportParams;
import com.example.reportservice.entity.Subscription;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ReportParamsMapper {


    @Mapping(target = "subscriptionId", source = "subscriptionId")
    @Mapping(target = "reportType", source = "reportType")
    @Mapping(target = "merchantId", source = "merchantId")
    @Mapping(target = "merchantsIncluded", source = "merchantsIncluded")
    @Mapping(target = "language", source = "language")
    ReportParams subscriptionToReportParams(Subscription subscriptionDto);

    default String mapFileFormat(FileFormat fileFormat) {
        return fileFormat.name();
    }

    default List<String> map(String value) {
        if (value == null || value.isEmpty()) {
            return List.of();
        }
        return Arrays.asList(value.split(","));
    }
    
}


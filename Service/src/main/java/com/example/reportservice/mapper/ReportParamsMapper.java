package com.example.reportservice.mapper;
import com.example.report.model.ReportParams;
import com.example.reportservice.model.SubscriptionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface ReportParamsMapper {

    ReportParamsMapper INSTANCE = Mappers.getMapper(ReportParamsMapper.class);

    @Mapping(target = "subscriptionId", source = "subscriptionId")
    @Mapping(target = "reportType", source = "reportType")
    @Mapping(target = "merchantId", source = "merchantId")
    @Mapping(target = "merchantsIncluded", source = "merchantsIncluded")
    @Mapping(target = "language", source = "language")
    ReportParams subscriptionDtoToReportParams(SubscriptionDto subscriptionDto);


}


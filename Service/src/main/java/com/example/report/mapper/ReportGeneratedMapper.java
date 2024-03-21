package com.example.report.mapper;

import com.example.report.entity.ReportGenerated;
import com.example.report.model.ReportDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportGeneratedMapper {

    ReportDto entityToDto(ReportGenerated entity);
}

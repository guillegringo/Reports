package com.example.report.service;

import com.example.report.entity.ReportGenerated;
import com.example.report.model.ReportDto;

import java.util.List;
import java.util.Optional;

public interface ReportService {
    public List<ReportDto> getAllReports();
    public Optional<ReportGenerated> getReportById(Long reportId);
}

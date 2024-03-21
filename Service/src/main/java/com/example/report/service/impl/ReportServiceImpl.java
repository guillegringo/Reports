package com.example.report.service.impl;

import com.example.report.dao.ReportGeneratedRepository;
import com.example.report.entity.ReportGenerated;
import com.example.report.mapper.ReportGeneratedMapper;
import com.example.report.model.ReportDto;
import com.example.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private ReportGeneratedRepository reportGeneratedRepository;

    @Autowired
    private ReportGeneratedMapper reportGeneratedMapper;

    @Override
    public List<ReportDto> getAllReports() {
        List<ReportGenerated> reportGeneratedList = reportGeneratedRepository.findAll();
        return reportGeneratedList.stream()
                .map(reportGeneratedMapper::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReportGenerated> getReportById(Long reportId) {
        return reportGeneratedRepository.findById(reportId);
    }
}

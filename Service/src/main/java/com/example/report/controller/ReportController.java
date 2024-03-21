package com.example.report.controller;

import com.example.report.entity.ReportGenerated;
import com.example.report.model.ReportDto;
import com.example.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @Operation(summary = "List all reports")
    @GetMapping
    public List<ReportDto> getAllReports() {
        return reportService.getAllReports();
    }


    // Descargar reporte por ID
    @Operation(summary = "Download a report by ID")
    @GetMapping("/{reportId}/download")
    public ResponseEntity<Resource> downloadReport(
            @Parameter(description = "ID of the report to download") @PathVariable Long reportId) {
        Optional<ReportGenerated> optionalReport = reportService.getReportById(reportId);
        if (optionalReport.isPresent()) {
            ReportGenerated report = optionalReport.get();
            ByteArrayResource resource = new ByteArrayResource(report.getFile());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + report.getFilename() + "\"");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found");
        }
    }
}

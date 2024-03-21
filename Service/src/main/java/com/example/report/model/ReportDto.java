package com.example.report.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "Report Generated")
public class ReportDto {

    @Schema(description = "ID of the report")
    private Long reportId;

    @Schema(description = "Institution ID", example = "1")
    private String institutionId;

    @Schema(description = "Subscription period", allowableValues = {"PDF", "CSV", "XLS"})
    private FileFormat format;

    @Schema(description = "Language of the report", example = "en")
    private String language;

    @Schema(description = "Filename of the report", example = "TRANSACTION_DAILY_20240321.csv")
    private String filename;

    @Schema(description = "Date of the report")
    private Date reportDate;
}

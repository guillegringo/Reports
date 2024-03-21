package com.example.report.model;

import com.example.report.model.FileFormat;
import com.example.report.model.Period;
import com.example.report.model.ReportType;
import com.example.report.model.SubcriptionStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SubscriptionDto {
    @Schema(description = "Subscription ID")
    private Long subscriptionId;

    @Schema(description = "Institution ID", example = "1")
    @NotNull(message = "Institution ID cannot be null")
    private String institutionId;

    @Schema(description = "Status of the subcription", example = "ACTIVE", defaultValue = "ACTIVE",
            allowableValues = {"ACTIVE", "INACTIVE"})
    @NotNull(message = "Status type cannot be null")
    private SubcriptionStatus status;

    @Schema(description = "Report type", example = "TRANSACTION", allowableValues = {"TRANSACTION", "AUTHORISATION"})
    @NotNull(message = "Report type cannot be null")
    private ReportType reportType;

    @Schema(description = "Merchant ID owner of the subcription", example = "123")
    @NotNull(message = "Merchant ID cannot be null")
    private String merchantId;

    @Schema(description = "Merchants included in the subscription", example = "[\"123\",\"321\",\"678\"]")
    private List<String> merchantsIncluded;

    @Schema(description = "Subscription period", defaultValue = "DAILY", allowableValues = {"DAILY", "WEEKLY", "MONTHLY"})
    private Period period;

    @Schema(description = "Subscription period", defaultValue = "CSV", allowableValues = {"PDF", "CSV", "XLS"})
    private FileFormat format;

    @Schema(description = "Report language", defaultValue = "en")
    private String language;
}


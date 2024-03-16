package com.example.reportservice.model;

import com.example.report.model.Period;
import com.example.report.model.ReportType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SubscriptionDto {
    @Schema(description = "Subscription ID")
    private Long subscriptionId;

    @Schema(description = "Report type", example = "TRANSACTION", allowableValues = {"TRANSACTION", "AUTHORISATION"})
    @NotNull(message = "Report type cannot be null")
    private ReportType reportType;

    @Schema(description = "Merchant ID owner of the subcription")
    @NotNull(message = "Merchant ID cannot be null")
    private String merchantId;

    @Schema(description = "Merchants included in the subscription", example = "[\"123\",\"321\",\"678\"]")
    private List<String> merchantsIncluded;

    @Schema(description = "Subscription period", defaultValue = "WEEKLY", allowableValues = {"DAILY", "WEEKLY", "MONTHLY"})
    private Period period;

    @Schema(description = "Report language", defaultValue = "en")
    private String language;
}


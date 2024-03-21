package com.example.report.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    String transactionId;
    String merchantId;
    BigDecimal amount;
    String currency;
    String AuthCode;

}

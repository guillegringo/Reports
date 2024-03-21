package com.example.report.service;

import com.example.report.entity.Transaction;

import java.util.Date;
import java.util.List;

public interface TransactionService {

    public List<Transaction> getTransactions(List<String> merchantsIncluded, String institutionId, Date from, Date to);
}

package com.example.report.service.impl;

import com.example.report.entity.Transaction;
import com.example.report.service.TransactionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    public List<Transaction> getTransactions(List<String> merchantsIncluded, String institutionId, Date from, Date to) {
        List<Transaction> transactions = new ArrayList<>();


        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId("T123456");
        transaction1.setMerchantId("MERCHANT_1");
        transaction1.setAmount(new BigDecimal("100.00"));
        transaction1.setCurrency("USD");
        transaction1.setAuthCode("ABC123");
        transactions.add(transaction1);


        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId("T789012");
        transaction2.setMerchantId("MERCHANT_2");
        transaction2.setAmount(new BigDecimal("50.00"));
        transaction2.setCurrency("EUR");
        transaction2.setAuthCode("DEF456");
        transactions.add(transaction2);

        try {
            System.out.println("select * from transactions - waiting for response");
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return transactions;
    }
}

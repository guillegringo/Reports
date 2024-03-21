package com.example.report.service.impl;

import com.example.report.dao.ReportGeneratedRepository;
import com.example.report.entity.ReportGenerated;
import com.example.report.entity.Transaction;
import com.example.report.model.FileFormat;
import com.example.report.model.ReportParams;
import com.example.report.service.ReportGeneratorService;
import com.example.report.service.TransactionService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jobrunr.JobRunrException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;

import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
@Qualifier("TRANSACTION")
public class TransactionReportGeneratorServiceImpl implements ReportGeneratorService {
    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private ReportGeneratedRepository reportGeneratedRepository;

    @Override
    public void generate(ReportParams params) {
        switch (params.getFormat()) {
            case CSV:
                generateCSVReport(params);
                break;
            case XLS:
                generateXLSReport(params);
                break;
            default:
                throw new JobRunrException("Report format is not implemented "+ params.getFormat().name(), true);
        }
    }

    private void generateCSVReport(ReportParams params) {
        List<Transaction> transactions = transactionService.getTransactions(params.getMerchantsIncluded()
                ,params.getInstitutionId()
                ,params.getFrom()
                ,params.getTo());
        StringBuilder csvContent = new StringBuilder();

        // add headers
        csvContent.append(getCSVHeaders(params)).append("\n");

        // add trx
        for (Transaction transaction : transactions) {
            csvContent.append(transaction.getTransactionId()).append(",");
            csvContent.append(transaction.getMerchantId()).append(",");
            csvContent.append(transaction.getAmount()).append(",");
            csvContent.append(transaction.getCurrency()).append(",");
            csvContent.append(transaction.getAuthCode()).append("\n");
        }

        saveReport(params, csvContent.toString());
    }

    private String getCSVHeaders(ReportParams params) {
        String columnNameTransactionId = messageSource.getMessage("report.transaction.columns.transactionId", null, new Locale(params.getLanguage()));
        String columnNameMerchantId = messageSource.getMessage("report.transaction.columns.merchantId", null, new Locale(params.getLanguage()));
        String columnNameAmount = messageSource.getMessage("report.transaction.columns.amount", null, new Locale(params.getLanguage()));
        String columnNameCurrency = messageSource.getMessage("report.transaction.columns.currency", null, new Locale(params.getLanguage()));
        String columnNameAuthCode = messageSource.getMessage("report.transaction.columns.authCode", null, new Locale(params.getLanguage()));

        return columnNameTransactionId + "," + columnNameMerchantId + "," + columnNameAmount + "," + columnNameCurrency + "," + columnNameAuthCode;
    }

    private void generateXLSReport(ReportParams params) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Transaction Report");

        Row headerRow = sheet.createRow(0);
        String[] columnNames = {
                messageSource.getMessage("report.transaction.columns.transactionId", null, new Locale(params.getLanguage())),
                messageSource.getMessage("report.transaction.columns.merchantId", null, new Locale(params.getLanguage())),
                messageSource.getMessage("report.transaction.columns.amount", null, new Locale(params.getLanguage())),
                messageSource.getMessage("report.transaction.columns.currency", null, new Locale(params.getLanguage())),
                messageSource.getMessage("report.transaction.columns.authCode", null, new Locale(params.getLanguage()))
        };
        for (int i = 0; i < columnNames.length; i++) {
            headerRow.createCell(i).setCellValue(columnNames[i]);
        }

        List<Transaction> transactions = transactionService.getTransactions(params.getMerchantsIncluded()
                ,params.getInstitutionId()
                ,params.getFrom()
                ,params.getTo());
        int rowNum = 1;
        for (Transaction transaction : transactions) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(transaction.getTransactionId());
            row.createCell(1).setCellValue(transaction.getMerchantId());
            row.createCell(2).setCellValue(transaction.getAmount().doubleValue());
            row.createCell(3).setCellValue(transaction.getCurrency());
            row.createCell(4).setCellValue(transaction.getAuthCode());
        }

        saveReport(params, workbook);
    }

    private void saveReport(ReportParams params, Object content) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            if (content instanceof String) {
                outputStream.write(((String) content).getBytes());
            } else if (content instanceof Workbook) {
                ((Workbook) content).write(outputStream);
            } else {
                throw new JobRunrException("content of the report is not compatible", true);
            }

            byte[] fileContent = outputStream.toByteArray();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            String formattedDate = dateFormat.format(new Date());

            ReportGenerated reportGenerated = new ReportGenerated();
            reportGenerated.setInstitutionId(params.getInstitutionId());
            reportGenerated.setFormat(params.getFormat().toString());
            reportGenerated.setLanguage(params.getLanguage());
            reportGenerated.setFilename(params.getReportType().toString() + "_" + params.getPeriod() + "_" + formattedDate + getFileExtension(params.getFormat()));
            reportGenerated.setSubscriptionId(params.getSubscriptionId());
            reportGenerated.setMerchantId(params.getMerchantId());
            reportGenerated.setFile(fileContent);
            reportGenerated.setReportDate(new Date());

            reportGeneratedRepository.save(reportGenerated);
        } catch (Exception e) {
            throw new JobRunrException("File save issue", true);

        }
    }

    private String getFileExtension(FileFormat format) {
        switch (format) {
            case CSV:
                return ".csv";
            case XLS:
                return ".xlsx";
            default:
                throw new JobRunrException("Report format is not implemented", true);
        }
    }
}

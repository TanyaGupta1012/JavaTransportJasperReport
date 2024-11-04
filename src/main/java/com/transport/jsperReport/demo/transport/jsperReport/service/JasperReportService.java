package com.transport.jsperReport.demo.transport.jsperReport.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRSaver;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JasperReportService {

    public byte[] generateReport(List<Map<String, Object>> data, Map<String, Object> parameters) throws Exception {
        // Load the .jrxml file
        InputStream reportStream = new ClassPathResource("/reports/transport_report.jrxml").getInputStream();

        // Compile the report to .jasper
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        // Use JRBeanCollectionDataSource to populate the report
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);

        // Fill the report with data and parameters
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export to PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}


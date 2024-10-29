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

//    public byte[] generateTransportReport() throws JRException {
//        // Load and compile the Jasper report file
//        InputStream jasperStream = getClass().getResourceAsStream("/reports/transport_report.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(jasperStream);
//
//        // Set parameters for the report
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("pLRNo", "0003");
//        parameters.put("pDate", "15-06-2021");
//        parameters.put("pVehicleNo", "PB00AA1234");
//        parameters.put("pGSTNo", "24AANFC9587Z1OC");
//        parameters.put("pConsignor", "CONNECT IT HUB");
//        parameters.put("pConsignee", "CONNECT IT HUB");
//        parameters.put("pCommodity", "IMP STEAM COAL");
//        parameters.put("pBillNo", "588");
//        parameters.put("pBillAmt", "2,35,545.00");
//        parameters.put("pTotalFreight", "1,14,497.50");
//        parameters.put("pGuaranteeCharges", "800.00");
//        parameters.put("pBiltyCharges", "100.00");
//        parameters.put("pAdvanceAmount", "15,000.00");
//        parameters.put("pRoundOff", "0.50");
//        parameters.put("pFreightToPay", "1,00,398.00");
//
//        // Adding more parameters for company details
//        parameters.put("companyTitle", "AAA Transport Company");
//        parameters.put("companyAddress", "123 Transport Street, New Delhi, India");
//        parameters.put("companyContact", "+91 12345 67890");
//        parameters.put("companyEmail", "contact@aaatransport.com");
//
//
//        // Fill the report with parameters and empty data source
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//
//        // Export the filled report to PDF format
//        return JasperExportManager.exportReportToPdf(jasperPrint);
//    }


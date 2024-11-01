package com.transport.jsperReport.demo.transport.jsperReport.controller;

import com.transport.jsperReport.demo.transport.jsperReport.service.JasperReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private JasperReportService reportService;

    @GetMapping("/transport")
    public ResponseEntity<byte[]> generateTransportReport() {
        try {
            // Dummy data for the report
            List<Map<String, Object>> data = new ArrayList<>();
            Map<String, Object> row = new HashMap<>();
            row.put("packages", "Basic Package");
            row.put("commodity", "Electronics");
            row.put("weight", "500kg");
            row.put("freight", "1000 USD");
            data.add(row);

            // Parameters for the report
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("companyName", "AAA TRANSPORT COMPANY");
            parameters.put("companyAddress", "Office No. 4, Aavishkar Building, Ward 12 B, Apna Nagar Char Rasta, Gandhidham, Kutch, Gujarat, 370201");
            parameters.put("companyContact", 123456789L);
            parameters.put("lrNumber", 5675L);
            parameters.put("date", new java.sql.Date(System.currentTimeMillis())); // Current date as an example
            parameters.put("vehicleNumber", "GJ-01-AB-1234");
            parameters.put("comingFrom", "Mumbai");
            parameters.put("goingTo", "Delhi");
            parameters.put("driverMobileNo", 9876543210L);
            parameters.put("TaxIdentificationNumber", "GST12345ABCD");
            parameters.put("GSTPaidBy", "Consignee");
            parameters.put("consignorAddress", "OFFICE NO 204, SECOND FLOOR, PLOT NO 108, SECTOR NO 8, ASHOPALAV GANDHIDHAM OWNERS ASSOSIATION, GANDHIDHAM, KACHCHH, GUJARAT - 370201");
            parameters.put("consigneeGST", "24AANFC9287A1ZC");
            parameters.put("consignorGST", "24AANFC9287A1ZC5");
            parameters.put("consigneeAddress", "OFFICE NO 204, SECOND FLOOR, PLOT NO 108, SECTOR NO 8, ASHOPALAV GANDHIDHAM OWNERS ASSOSIATION, GANDHIDHAM, KACHCHH, GUJARAT - 370201");
            parameters.put("consigneeName", "CONNECT IT HUB");
            parameters.put("consignorName", "CONNECT IT HUB");
//            parameters.put("BE_No_BillOfEntryNumber", "");
//            parameters.put("CBM_CubicMeter", "");
//            parameters.put("Bill_Number", "558");
            parameters.put("AccountHolder", "Yash");
            parameters.put("AccountBranch", "GANDHIDHAM");
            parameters.put("AccountNumber", "10101010103");
            parameters.put("IFSC_Code", "YES0001221");
            parameters.put("Total_freight_amount", 14497.5);
            parameters.put("Gaurantee_Charges", 800.0);
            parameters.put("Bilty_Charges", 100.0);
            parameters.put("AdvanceAmount", 15000.01);
            parameters.put("Round_Off", 0.5);
            parameters.put("Freight_To_Pay", 100398.0);


            // Generate the report as PDF
            byte[] pdfReport = reportService.generateReport(data, parameters);

            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=transport_report.pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfReport);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

//    @GetMapping("/transport")
//    public ResponseEntity<byte[]> generateTransportReport() {
//        try {
//            byte[] pdfReport = reportService.generateTransportReport();
//
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_PDF);
//            headers.setContentDispositionFormData("filename", "TransportReport.pdf");
//
//            return new ResponseEntity<>(pdfReport, headers, HttpStatus.OK);
//
//        } catch (Exception e) {
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }

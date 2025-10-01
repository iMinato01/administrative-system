package com.gg.administrative_system_backend.supplier.report.controller;

import com.gg.administrative_system_backend.supplier.report.service.SupplierReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suppliers")
public class SupplierReportController {
    private final SupplierReportService supplierReportService;
    @GetMapping("/report")
    public ResponseEntity<byte[]> getReport() throws Exception{
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_PDF).body(supplierReportService.getReport());
    }
}

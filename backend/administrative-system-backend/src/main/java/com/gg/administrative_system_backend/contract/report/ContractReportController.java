package com.gg.administrative_system_backend.contract.report;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractReportController {
    private final ContractReportService contractReportService;
    @GetMapping("/report")
    public ResponseEntity<byte[]> getReport() throws Exception{
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; contracts.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(contractReportService.exportPdf());
    }
}

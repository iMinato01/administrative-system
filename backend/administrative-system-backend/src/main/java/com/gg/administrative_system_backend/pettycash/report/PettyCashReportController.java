package com.gg.administrative_system_backend.pettycash.report;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@RequestMapping("/pettycash/report")
public class PettyCashReportController {
    private final PettyCashReportService pettyCashReportService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> generateReport(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .contentType(MediaType.APPLICATION_PDF)
                .body(pettyCashReportService.exportPdf(id));
    }
}

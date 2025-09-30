package com.gg.administrative_system_backend.company.report;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller that handles company report generation requests.<br>
 * Provides an endpoint to generate and download reports in PDF format.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyReportController {
    private final CompanyReportService companyReportService;

    /**
     * Generates a company report and returns it as a PDF file.
     *
     * @return A {@link ResponseEntity} containing the PDF file as a byte array.
     * The response has a content type of {@link MediaType#APPLICATION_PDF}.
     * @throws Exception Exception if an error occurs during report generation.
     */
    @GetMapping("/report")
    public ResponseEntity<byte[]> getReport() throws Exception {
        return ResponseEntity.status(200).contentType(MediaType.APPLICATION_PDF).body(companyReportService.getReport());
    }
}

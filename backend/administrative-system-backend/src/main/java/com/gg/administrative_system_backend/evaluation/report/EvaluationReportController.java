package com.gg.administrative_system_backend.evaluation.report;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
@AllArgsConstructor
public class EvaluationReportController {
    private final EvaluationReportService evaluationReportService;

    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long id) throws Exception {
        return ResponseEntity.status(200)
                .contentType(MediaType.APPLICATION_PDF)
                .body(evaluationReportService.exportPdf(id));
    }
}

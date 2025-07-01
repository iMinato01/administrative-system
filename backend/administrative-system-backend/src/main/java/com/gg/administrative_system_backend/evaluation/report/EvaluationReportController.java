package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.repository.EvaluationRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
@AllArgsConstructor
public class EvaluationReportController {
    private final EvaluationReportService evaluationReportService;
    private final EvaluationRepository evaluationRepository;
    @GetMapping("/report/{id}")
    public ResponseEntity<byte[]> getReport(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; evaluation"+id+".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(evaluationReportService.exportPdf(id));
    }
}

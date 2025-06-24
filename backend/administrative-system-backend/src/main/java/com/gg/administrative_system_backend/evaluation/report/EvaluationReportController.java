package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.EvaluationRepository;
import com.gg.administrative_system_backend.supplier.SupplierRepository;
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
        String name = evaluationRepository.findSupplierNameByEvaluationId(id).replaceAll("[^a-zA-Z]", "");
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;" + name + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(evaluationReportService.exportPdf(id));
    }
}

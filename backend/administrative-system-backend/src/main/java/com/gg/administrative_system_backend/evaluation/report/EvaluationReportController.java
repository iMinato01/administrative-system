package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.response.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evaluations")
@AllArgsConstructor
public class EvaluationReportController {
    private final EvaluationReportService evaluationReportService;
    @GetMapping("/report/{id}")
    public ResponseEntity<ApiResponse<byte[]>> getReport(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(200)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=evaluation_" + id + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(ApiResponse.of(200, HttpStatus.OK.getReasonPhrase(), evaluationReportService.exportPdf(id)));
    }
}

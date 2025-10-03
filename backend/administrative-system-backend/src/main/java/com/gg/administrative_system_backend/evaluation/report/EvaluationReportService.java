package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.evaluation.service.EvaluationService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.ReportHelper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service responsible for generating PDF reports for {@code Evaluation} entities.
 *
 * <p>This service gathers detailed evaluation and supplier information, maps the data
 * into a structured format, and delegates the PDF generation to a helper utility.</p>
 */
@Service
@AllArgsConstructor
public class EvaluationReportService {
    private final EvaluationService evaluationService;
    private final ReportHelper reportHelper;

    /**
     * Generates a PDF report for a specific {@code Evaluation} entity.
     *
     * @param id Unique identifier of the {@code Evaluation} entity to be reported.
     * @return A byte array representing the generated PDF document.
     * @throws Exception If an error occurs during the report generation process.
     */
    public byte[] exportPdf(Long id) throws Exception {
        Evaluation evaluation = evaluationService.findEvaluation(id);
        return reportHelper.generatePdf(Report.EVALUATION, List.of(evaluation));
    }
}

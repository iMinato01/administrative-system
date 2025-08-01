package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.evaluation.service.EvaluationService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.util.ReportHelper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Supplier supplier = evaluation.getSupplier();
        List<Integer> info = evaluation.getInformationScores();
        List<Integer> gene = evaluation.getGeneralScores();
        List<Integer> deli = evaluation.getDeliveryScores();
        List<Integer> qual = evaluation.getQualityScores();
        Map<String, Object> reportData = new HashMap<>();
        reportHelper.mapElements(supplier::getName, reportData, "name");
        reportHelper.mapElements(supplier::getRfc, reportData, "rfc");
        reportHelper.mapElements(supplier::getEmail, reportData, "email");
        reportHelper.mapElements(supplier::getPhoneNumber, reportData, "phoneNumber");
        reportHelper.mapElements(supplier::getServices, reportData, "services");
        reportHelper.mapElements(evaluation::evaluationDateToString, reportData, "evaluationDate");
        reportHelper.mapElements(evaluation::nextEvaluationToString, reportData, "nextEvaluation");
        reportHelper.mapElements(info, reportData, "info");
        reportHelper.mapElements(gene, reportData, "gene");
        reportHelper.mapElements(deli, reportData, "deli");
        reportHelper.mapElements(qual, reportData, "qual");
        return reportHelper.generatePdf(Report.EVALUATION.getPath(), List.of(reportData));
    }
}

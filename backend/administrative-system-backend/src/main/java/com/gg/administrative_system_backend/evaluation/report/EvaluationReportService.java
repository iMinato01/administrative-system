package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.evaluation.service.EvaluationService;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportPaths;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EvaluationReportService {
    private final EvaluationService evaluationService;
    private final ReportHelper reportHelper;
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
        return reportHelper.generatePdf(ReportPaths.EVALUATION_REPORT, List.of(reportData));
    }
}

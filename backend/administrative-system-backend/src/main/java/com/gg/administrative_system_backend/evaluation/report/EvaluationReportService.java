package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.Evaluation;
import com.gg.administrative_system_backend.evaluation.EvaluationService;
import com.gg.administrative_system_backend.message.Params;
import com.gg.administrative_system_backend.supplier.Supplier;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EvaluationReportService {
    private final EvaluationService evaluationService;
    public byte[] exportPdf(Long id) throws Exception {
        Evaluation evaluation = evaluationService.findEvaluation(id);

        Map<String, Object> reportData = new HashMap<>();

        Supplier supplier = evaluation.getSupplier();
        reportData.put("name", supplier.getName());
        reportData.put("rfc", supplier.getRfc());
        reportData.put("phoneNumber", supplier.getPhoneNumber());
        reportData.put("services", supplier.getServices());
        reportData.put("evaluationDate", evaluation.getEvaluationDate());
        reportData.put("nextEvaluation", evaluation.getNextEvaluation());
        List<Integer> info = evaluation.getInformationScores();
        for(int i = 0; i < evaluation.getInformationScores().size(); i++){
            reportData.put("info+" + i, info.size() < Params.INFORMATION_SCORE ? info.get(i): null);
        }
        List<Map<String, Object>> dataList = List.of(reportData);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

        InputStream inputStream = getClass().getResourceAsStream(Params.EVALUATION_REPORT_FORMAT);
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}

package com.gg.administrative_system_backend.evaluation.report;

import com.gg.administrative_system_backend.evaluation.Evaluation;
import com.gg.administrative_system_backend.evaluation.EvaluationService;
import com.gg.administrative_system_backend.supplier.Supplier;
import com.gg.administrative_system_backend.util.ReportPaths;
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
        Supplier supplier = evaluation.getSupplier();
        List<Integer> info = evaluation.getInformationScores();
        List<Integer> gene = evaluation.getGeneralScores();
        List<Integer> deli = evaluation.getDeliveryScores();
        List<Integer> qual = evaluation.getQualityScores();
        Map<String, Object> reportData = new HashMap<>();
        mapElements(supplier::getName, reportData, "name");
        mapElements(supplier::getRfc, reportData, "rfc");
        mapElements(supplier::getPhoneNumber, reportData, "phoneNumber");
        mapElements(supplier::getServices, reportData, "services");
        mapElements(evaluation::evaluationDateToString, reportData, "evaluationDate");
        mapElements(evaluation::nextEvaluationToString, reportData, "nextEvaluation");
        mapElements(info, reportData, "info");
        //mapElements(gene, reportData, "gene");
        mapElements(deli, reportData, "deli");
        mapElements(qual, reportData, "qual");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(List.of(reportData));

        InputStream inputStream = getClass().getResourceAsStream(ReportPaths.EVALUATION_REPORT);
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
    public <T> void mapElements(java.util.function.Supplier<T> newElement, Map<String, Object> dataMap, String fieldName){
        dataMap.put(fieldName, newElement.get());
    }
    public void mapElements(List<Integer> newElements, Map<String, Object> dataMap, String fieldName){
        for(int i = 0; i < newElements.size(); i++){
            dataMap.put(fieldName+i, newElements.get(i));
        }
    }

}

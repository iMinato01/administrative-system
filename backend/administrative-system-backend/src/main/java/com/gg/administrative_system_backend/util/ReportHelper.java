package com.gg.administrative_system_backend.util;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
public class ReportHelper {
    public <T> void mapElements(java.util.function.Supplier<T> newElement, Map<String, Object> dataMap, String fieldName){
        dataMap.put(fieldName, newElement.get());
    }
    public void mapElements(List<Integer> newElements, Map<String, Object> dataMap, String fieldName){
        for(int i = 0; i < newElements.size(); i++){
            dataMap.put(fieldName+i, newElements.get(i));
        }
    }
    public <T> byte[] generatePdf(String reportPath, List<T> elements) throws Exception{
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(elements);
        InputStream format = getClass().getResourceAsStream(reportPath);
        //<-- Se compila en cada llamada por cambios continuos en los formatos, pendiente por optimizar -->
        JasperReport jasperReport = JasperCompileManager.compileReport(format);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

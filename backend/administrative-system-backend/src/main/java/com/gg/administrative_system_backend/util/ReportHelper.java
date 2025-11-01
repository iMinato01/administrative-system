package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.shared.ReportCache;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Component
@AllArgsConstructor
public class ReportHelper {
    private final ReportCache reportCache;

    public <T> byte[] generatePdf(Report report, Map<String, Object> parameters, List<T> elements) throws Exception {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(elements);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportCache.getCompiled(report), parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public <T> byte[] generatePdf(Report report, List<T> elements) throws Exception {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(elements);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportCache.getCompiled(report), null, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

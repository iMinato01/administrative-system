package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.shared.report.ReportRoute;
import com.gg.administrative_system_backend.shared.report.ReportCache;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Component
@AllArgsConstructor
public class ReportHelper {
    private final ReportCache reportCache;

    public <T> byte[] generatePdf(ReportRoute reportRoute, Map<String, Object> parameters, List<T> elements) throws Exception {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(elements);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportCache.getCompiled(reportRoute), parameters, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    public <T> byte[] generatePdf(ReportRoute reportRoute, List<T> elements) throws Exception {
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(elements);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reportCache.getCompiled(reportRoute), null, dataSource);
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}

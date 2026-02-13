package com.gg.administrative_system_backend.shared.report;

import com.gg.administrative_system_backend.exception.ReportNotFoundException;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ReportCache {
    Map<ReportRoute, JasperReport> compiledReports = new HashMap<>();
    public JasperReport getCompiled(ReportRoute key){
        JasperReport compiled = compiledReports.get(key);
        if(compiled == null){
            throw new ReportNotFoundException(ExceptionMessage.REPORT_NOT_FOUND.format(key.name()));
        }
        return compiled;
    }
    public void addCompiled(ReportRoute key, JasperReport compiled){
        compiledReports.put(key, compiled);
    }
}

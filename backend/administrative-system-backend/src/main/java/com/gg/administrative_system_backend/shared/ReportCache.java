package com.gg.administrative_system_backend.shared;

import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ReportCache {
    Map<Report, JasperReport> compiledReports = new HashMap<>();
    public JasperReport getCompiled(Report key){
        return compiledReports.get(key);
    }
    public void addCompiled(Report key, JasperReport compiled){
        compiledReports.put(key, compiled);
    }
}

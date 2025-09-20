package com.gg.administrative_system_backend.shared;

import com.gg.administrative_system_backend.exception.ReportNotFoundException;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class ReportCache {
    Map<Report, JasperReport> compiledReports = new HashMap<>();
    public JasperReport getCompiled(Report key){
        JasperReport compiled = compiledReports.get(key);
        if(compiled == null){
            throw new ReportNotFoundException(GenericMessage.REPORT_NOT_FOUND.format(key.name()));
        }
        return compiled;
    }
    public void addCompiled(Report key, JasperReport compiled){
        compiledReports.put(key, compiled);
    }
}

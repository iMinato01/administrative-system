package com.gg.administrative_system_backend.startup;

import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.shared.ReportCache;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
@AllArgsConstructor
public class ReportCompiler implements CommandLineRunner {
    private final ReportCache reportCache;

    @Override
    public void run(String... args) throws Exception {
        for (Report report : Report.values()) {
            try {
                if (report.getPath() == null || report.getPath().isBlank()) {
                    throw new IllegalArgumentException();
                }
                InputStream inputStream = getClass().getResourceAsStream(report.getPath());
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                reportCache.addCompiled(report, jasperReport);
                System.out.println(GenericMessage.COMPILE_SUCCESS.format(report.getPath()));
            } catch (NullPointerException exception) {
                System.out.println(GenericMessage.COMPILE_FAIL.format(report.name(), report.getPath()));
            } catch (IllegalArgumentException exception) {
                System.out.println(GenericMessage.MISSING_PATH.format(report.name()));
            }
        }
    }
}

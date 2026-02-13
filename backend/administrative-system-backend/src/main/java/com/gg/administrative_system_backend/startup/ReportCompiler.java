package com.gg.administrative_system_backend.startup;

import com.gg.administrative_system_backend.shared.report.ReportRoute;
import com.gg.administrative_system_backend.shared.report.ReportCache;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.JRException;
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
    public void run(String... args) throws JRException {
        for (ReportRoute reportRoute : ReportRoute.values()) {
            try {
                if (reportRoute.getPath() == null || reportRoute.getPath().isBlank()) {
                    throw new IllegalArgumentException();
                }
                InputStream inputStream = getClass().getResourceAsStream(reportRoute.getPath());
                JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
                reportCache.addCompiled(reportRoute, jasperReport);
                System.out.println(GenericMessage.COMPILE_SUCCESS.format(reportRoute.getPath()));
            } catch (NullPointerException exception) {
                System.out.println(GenericMessage.COMPILE_FAIL.format(reportRoute.name(), reportRoute.getPath()));
            } catch (IllegalArgumentException exception) {
                System.out.println(GenericMessage.MISSING_PATH.format(reportRoute.name()));
            }
        }
    }
}

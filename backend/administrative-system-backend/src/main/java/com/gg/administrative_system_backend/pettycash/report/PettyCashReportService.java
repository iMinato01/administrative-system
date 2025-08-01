package com.gg.administrative_system_backend.pettycash.report;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.service.PettyCashService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PettyCashReportService {
    private final CompanyService companyService;
    private final ReportUtils reportUtils;
    private final ReportHelper reportHelper;
    private final PettyCashService pettyCashService;

    public byte[] exportPdf() throws Exception {
        Map<String, Object> parameters = new HashMap<>();
        Company company = companyService.findCompany(1L); // Simulated with Company ID --> 1
        PettyCash pettyCash = pettyCashService.findPettyCash(1L);
        parameters.put("creationDate", pettyCash.getDate().toString());

        reportUtils.setReportHeader(parameters, company);
        return reportHelper.generatePdf(Report.PETTY_CASH.getPath(), parameters, pettyCash.getExpenses());
    }
}

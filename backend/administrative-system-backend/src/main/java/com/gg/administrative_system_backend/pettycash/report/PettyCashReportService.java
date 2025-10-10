package com.gg.administrative_system_backend.pettycash.report;

import com.gg.administrative_system_backend.auth.CompanyDetails;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.pettycash.service.PettyCashService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.AuthenticationUtils;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PettyCashReportService {
    private final CompanyService companyService;
    private final ReportHelper reportHelper;
    private final PettyCashService pettyCashService;

    public byte[] exportPdf(Long id) throws Exception {
        CompanyDetails companyDetails = (CompanyDetails) AuthenticationUtils.getAuthentication().getPrincipal();
        Company company = companyService.findCompany(companyDetails.getId());
        PettyCash pettyCash = pettyCashService.findPettyCash(id);
        return reportHelper.generatePdf(Report.PETTY_CASH, ReportUtils.getHeader(company), pettyCash.getExpenses());
    }
}

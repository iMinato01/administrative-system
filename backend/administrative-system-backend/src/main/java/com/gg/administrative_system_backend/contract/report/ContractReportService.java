package com.gg.administrative_system_backend.contract.report;

import com.gg.administrative_system_backend.auth.CompanyDetails;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.AuthenticationUtils;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for generating PDF reports for {@code Contract} entities.
 */
@Service
@RequiredArgsConstructor
public class ContractReportService {
    private final CompanyService companyService;
    private final ContractService contractService;
    private final ReportHelper reportHelper;

    public byte[] exportPdf() throws Exception {
        CompanyDetails companyDetails = (CompanyDetails) AuthenticationUtils.getAuthentication().getPrincipal();
        Company company = companyService.findCompany(companyDetails.getId());
        return reportHelper.generatePdf(Report.CONTRACT, ReportUtils.getHeader(company), contractService.findAll());
    }
}

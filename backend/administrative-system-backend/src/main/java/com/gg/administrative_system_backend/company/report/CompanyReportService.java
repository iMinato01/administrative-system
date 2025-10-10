package com.gg.administrative_system_backend.company.report;

import com.gg.administrative_system_backend.auth.CompanyDetails;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.AuthenticationUtils;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service responsible for generating company reports in PDF format.<br>
 * Retrieves the authenticated company, gathers its data, and delegates the report generation to the {@link ReportHelper}.
 */
@Service
@RequiredArgsConstructor
public class CompanyReportService {
    private final CompanyService companyService;
    private final ReportHelper reportHelper;

    /**
     * Generates a PDF report containing company information.
     *
     * @return A byte array representing the generated PDF report.
     * @throws Exception if an error occurs while generating the report.
     */
    public byte[] getReport() throws Exception {
        CompanyDetails companyDetails = (CompanyDetails) AuthenticationUtils.getAuthentication().getPrincipal();
        Company company = companyService.findCompany(companyDetails.getId());
        return reportHelper.generatePdf(Report.COMPANY, ReportUtils.getHeader(company), companyService.findAll());
    }
}

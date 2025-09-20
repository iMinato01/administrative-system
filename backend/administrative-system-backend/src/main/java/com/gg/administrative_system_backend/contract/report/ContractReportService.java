package com.gg.administrative_system_backend.contract.report;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Service responsible for generating PDF reports for {@code Contract} entities.
 *
 * <p>This service collects contract data, retrieves the associated company,
 * sets the report parameters, and delegates the PDF generation to helper utilities.</p>
 *
 * <p><b>Note:</b> The company ID is currently hardcoded for simulation purposes.
 * In future implementations, this will be dynamically retrieved from the currently authenticated session
 * (JWT).</p>
 */
@Service
@AllArgsConstructor
public class ContractReportService {
    private final CompanyService companyService;
    private final ContractService contractService;
    private final ReportHelper reportHelper;
    private final ReportUtils reportUtils;

    /**
     * Exports a PDF report containing all {@code Contract} entities,
     * including the header data from the associated {@code Company}.
     *
     * @return A byte array representing the generated PDF document.
     * @throws Exception if an error occurs during data retrieval or report generation.
     */
    public byte[] exportPdf() throws Exception{
        Map<String, Object> parameters = new HashMap<>();
        Company company = companyService.findCompany(1L); // Simulated with Company ID --> 1
        reportUtils.setReportHeader(parameters, company);
        return reportHelper.generatePdf(Report.CONTRACT, parameters, contractService.findAll());
    }
}

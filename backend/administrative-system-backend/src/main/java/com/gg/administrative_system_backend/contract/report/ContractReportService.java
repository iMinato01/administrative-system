package com.gg.administrative_system_backend.contract.report;

import com.gg.administrative_system_backend.company.Company;
import com.gg.administrative_system_backend.company.CompanyService;
import com.gg.administrative_system_backend.contract.ContractRepository;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportPaths;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContractReportService {
    private final CompanyService companyService;
    private final ContractRepository contractRepository;
    private final ReportHelper reportHelper;
    private final ReportUtils reportUtils;
    public byte[] exportPdf() throws Exception{
        Map<String, Object> parameters = new HashMap<>();
        //Simular sesion con empresa 1 -->
        Company company = companyService.findCompany(1L);
        reportUtils.setReportHeader(parameters, company);
        return reportHelper.generatePdf(ReportPaths.CONTRACT_REPORT, parameters, contractRepository.findAll());
    }
}

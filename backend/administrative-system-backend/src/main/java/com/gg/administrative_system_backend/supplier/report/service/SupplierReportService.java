package com.gg.administrative_system_backend.supplier.report.service;

import com.gg.administrative_system_backend.auth.CompanyDetails;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.shared.Report;
import com.gg.administrative_system_backend.supplier.repository.SupplierRepository;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
import com.gg.administrative_system_backend.util.AuthenticatonUtils;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SupplierReportService {
    private final ReportHelper reportHelper;
    private final CompanyService companyService;
    private final SupplierRepository supplierRepository;
    public byte[] getReport() throws Exception{
        CompanyDetails companyDetails = (CompanyDetails) AuthenticatonUtils.getAuthentication().getPrincipal();
        Company company = companyService.findCompany(companyDetails.getId());
        return reportHelper.generatePdf(Report.SUPPLIER, ReportUtils.getHeader(company), supplierRepository.findAll());
    }
}

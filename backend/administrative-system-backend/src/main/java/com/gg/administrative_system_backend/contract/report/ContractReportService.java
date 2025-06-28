package com.gg.administrative_system_backend.contract.report;

import com.gg.administrative_system_backend.contract.ContractRepository;
import com.gg.administrative_system_backend.contract.ContractService;
import com.gg.administrative_system_backend.util.ReportHelper;
import com.gg.administrative_system_backend.util.ReportPaths;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor
public class ContractReportService {
    private final ContractService contractService;
    private final ContractRepository contractRepository;
    private final ReportHelper reportHelper;
    public byte[] exportPdf() throws Exception{

        Map<String, Object> parameters = new HashMap<>();
        return reportHelper.generatePdf(ReportPaths.CONTRACT_REPORT, parameters, contractRepository.findAll());
    }
}

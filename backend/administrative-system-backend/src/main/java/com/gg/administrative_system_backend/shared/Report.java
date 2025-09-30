package com.gg.administrative_system_backend.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Report {
    CONTRACT("/reports/contract_report.jrxml", "contrato"),
    SUPPLIER("", "proveedor"),
    EVALUATION("/reports/evaluation_report.jrxml", "evaluación"),
    COMPANY("", "empresa"),
    PETTY_CASH("/reports/petty_cash_report.jrxml", "caja chica");
    COMPANY("/reports/companies_report.jrxml", "empresa"),
    private final String path;
    private final String name;
}

package com.gg.administrative_system_backend.shared;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Report {
    CONTRACT("/reports/contract_report.jrxml", "contrato"),
    SUPPLIER("/reports/supplier_report.jrxml", "proveedor"),
    EVALUATION("/reports/evaluation_report.jrxml", "evaluación"),
    COMPANY("/reports/company_report.jrxml", "empresa"),
    PETTY_CASH("/reports/petty_cash_report.jrxml", "caja chica"),
    ORDER("/reports/order_report.jrxml", "orden");
    private final String path;
    private final String name;
}

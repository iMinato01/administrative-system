package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.company.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@AllArgsConstructor
public class ReportUtils {
    private final ReportHelper reportHelper;
    public void setReportHeader(Map<String, Object> parameters, Company company){
        reportHelper.mapElements(company::getName, parameters, "NAME");
        reportHelper.mapElements(company::getRfc, parameters, "RFC");
        reportHelper.mapElements(company::getPhoneNumber, parameters, "PHONE_NUMBER");
        reportHelper.mapElements(company::getStreet, parameters, "STREET");
        reportHelper.mapElements(company::getPostalCode, parameters, "POSTAL_CODE");
        reportHelper.mapElements(company::getExteriorNumber, parameters, "EXTERIOR_NUMBER");
        reportHelper.mapElements(company::getInteriorNumber, parameters, "INTERIOR_NUMBER");
        reportHelper.mapElements(company::getLocality, parameters, "LOCALITY");
        reportHelper.mapElements(company::getMunicipality, parameters, "MUNICIPALITY");
        reportHelper.mapElements(company::getState, parameters, "STATE");
    }
}

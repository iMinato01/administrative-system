package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.company.entity.Company;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReportUtils {
    public static Map<String, Object> getHeader(Company company){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", company.getId());
        parameters.put("NAME", company.getName());
        parameters.put("PASSWORD", company.getPassword());
        parameters.put("ROLE", company.getRole().name());
        parameters.put("STATUS", company.isStatus());
        parameters.put("RFC", company.getRfc());
        parameters.put("PHONE_NUMBER", company.getPhoneNumber());
        parameters.put("STATE", company.getState());
        parameters.put("MUNICIPALITY", company.getMunicipality());
        parameters.put("LOCALITY", company.getLocality());
        parameters.put("POSTAL_CODE", company.getPostalCode());
        parameters.put("STREET", company.getStreet());
        parameters.put("INTERIOR_NUMBER", company.getInteriorNumber());
        parameters.put("EXTERIOR_NUMBER", company.getExteriorNumber());
        return parameters;
    }
}

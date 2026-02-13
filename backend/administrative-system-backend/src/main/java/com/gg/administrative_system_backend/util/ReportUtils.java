package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@AllArgsConstructor
public class ReportUtils {
    public static Map<String, Object> getCompanyHeader(Company company){
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

    public static Map<String, Object> getPettyCashHeader(Company company, PettyCash pettyCash){
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
        parameters.put("CREATION_DATE", pettyCash.getDate());
        parameters.put("TYPE", pettyCash.getType().name());
        parameters.put("TOTAL", pettyCash.getTotal());
        Map<String, BigDecimal> totalByContract = new HashMap<>();
        List<ExpensesByContractDTO> expensesByContract =  new ArrayList<>();
        for(Expense currentExpense: pettyCash.getExpenses()){
            String key = currentExpense.getContract().getName();
            if(!totalByContract.containsKey(key)) {
                totalByContract.put(key, currentExpense.getAmount());
            } else {
                totalByContract.replace(key, currentExpense.getAmount().add(totalByContract.get(key)));
            }
        }
        for(Map.Entry<String, BigDecimal> totalByContractEntry: totalByContract.entrySet()) {
            expensesByContract.add(new ExpensesByContractDTO(totalByContractEntry.getKey(), totalByContractEntry.getValue()));
        }
        parameters.put("EXPENSES_BY_CONTRACT", expensesByContract);
        return parameters;
    }

    public static Map<String, Object> getOrderHeader(Order order){
        Map<String, Object> parameters = new HashMap<>();
        Supplier supplier = order.getSupplier();
        Company company = order.getCompany();
        parameters.put("COMPANY_NAME",company.getName());
        parameters.put("DATE", order.getDate());
        parameters.put("SUPPLIER_NAME", supplier.getName());
        parameters.put("SUPPLIER_RFC", supplier.getRfc());
        parameters.put("STREET", supplier.getStreet());
        parameters.put("EXTERIOR_NUMBER", supplier.getExteriorNumber());
        parameters.put("INTERIOR_NUMBER", supplier.getInteriorNumber());
        parameters.put("LOCALITY", supplier.getLocality());
        parameters.put("MUNICIPALITY", supplier.getMunicipality());
        parameters.put("STATE", supplier.getState());
        parameters.put("POSTAL_CODE", supplier.getPostalCode());
        parameters.put("SERIE", String.format("ODC-%S-%d", order.getSerie(), order.getFol()));
        parameters.put("SUPPLIER_PHONE", supplier.getPhoneNumber());
        parameters.put("PAY_METHOD", order.getPayMethod().getCode());
        parameters.put("CFDI_USAGE", order.getCfdiUsage().name());
        parameters.put("PAY_CONDITION", order.getPayCondition().name());
        parameters.put("PAY_METHOD_VALUE", order.getPayMethod().getDescription());
        parameters.put("CFDI_USAGE_VALUE", order.getCfdiUsage().getDescription());
        parameters.put("PAY_CONDITION_VALUE", order.getPayCondition().getDescription());
        parameters.put("REFERENCE", order.getReference());
        parameters.put("ETA", order.getEta());
        parameters.put("SUPPLIER_BANK", order.getBank());
        parameters.put("SUPPLIER_ACCOUNT", order.getAccount());
        parameters.put("SUPPLIER_CLABE", order.getClabe());
        parameters.put("SUBTOTAL", order.getSubtotal());
        parameters.put("IVA", order.getIva());
        parameters.put("ISR", order.getIsr());
        parameters.put("TOTAL", order.getTotal());
        return parameters;
    }
}

package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.contract.entity.Contract;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class ContractServiceTest {
    @Test
    public void testSaveContract(){
        Contract contract = Contract.builder().name("test").totalExpenses(new BigDecimal(0)).build();

    }
}

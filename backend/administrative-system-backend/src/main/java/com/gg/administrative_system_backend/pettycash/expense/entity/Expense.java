package com.gg.administrative_system_backend.pettycash.expense.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.pettycash.entity.PettyCash;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String description;
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "petty_cash_id")
    @JsonBackReference
    private PettyCash pettyCash;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    @JsonBackReference
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id")
    @JsonBackReference
    private Contract contract;
}

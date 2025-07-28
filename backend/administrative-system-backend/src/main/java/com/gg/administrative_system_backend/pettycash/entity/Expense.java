package com.gg.administrative_system_backend.pettycash.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private String supplier;
    private String description;
    private String contract;
    private BigDecimal amount;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "petty_cash_id")
    @JsonBackReference
    private PettyCash pettyCash;
}

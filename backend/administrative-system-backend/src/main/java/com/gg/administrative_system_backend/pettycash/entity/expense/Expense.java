package com.gg.administrative_system_backend.pettycash.entity.expense;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gg.administrative_system_backend.pettycash.entity.pettycash.PettyCash;
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
    private String supplier; // Pendiente por referencia
    private String description;
    private String contract; // Pendiente por referencia
    private BigDecimal total;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "petty_cash_id")
    @JsonBackReference
    private PettyCash pettyCash;
}

package com.gg.administrative_system_backend.pettycash.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gg.administrative_system_backend.shared.ExpenseType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PettyCash {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ExpenseType type;
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @OneToMany(mappedBy = "pettyCash", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Expense> expenses;
    @Builder.Default
    private BigDecimal total = BigDecimal.ZERO;

    public BigDecimal calculateTotal() {
        BigDecimal bigDecimal = BigDecimal.ZERO;
        if (!expenses.isEmpty()) {
            for (Expense expense : expenses) {
                bigDecimal = bigDecimal.add(expense.getAmount());
            }
        }
        return bigDecimal;
    }
}

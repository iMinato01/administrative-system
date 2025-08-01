package com.gg.administrative_system_backend.contract.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Contract {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        @Builder.Default
        private boolean status = true;
        @Builder.Default
        private BigDecimal totalExpenses = BigDecimal.ZERO;
        public void addAmount(BigDecimal value){
                totalExpenses = totalExpenses.add(value);
        }
}

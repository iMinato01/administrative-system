package com.gg.administrative_system_backend.evaluation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gg.administrative_system_backend.supplier.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate evaluationDate;
    private LocalDate nextEvaluation;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    @JsonBackReference
    private Supplier supplier;
    @CollectionTable
    private List<Integer> informationScores = new ArrayList<>(); // Almacenará 4 valores, del 0-4 (puntuaciones)
    @CollectionTable
    private List<Integer> generalScores = new ArrayList<>(); // Almacenará 8 valores, 10, 20 y 60 (puntuaciones)
    @CollectionTable
    private List<Integer> deliveryScores = new ArrayList<>(); //Almacenará 4 valores, del 0-4 (puntuaciones)
    @CollectionTable
    private List<Integer> qualityScores = new ArrayList<>(); // Almacenará 2 valores, del 0-4 (puntuaciones)
}

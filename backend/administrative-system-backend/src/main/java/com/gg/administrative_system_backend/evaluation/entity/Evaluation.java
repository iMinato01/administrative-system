package com.gg.administrative_system_backend.evaluation.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
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
    @ElementCollection
    @CollectionTable(name = "evaluation_information_score", joinColumns = @JoinColumn(name = "evaluation_id"))
    @Column(name = "score")
    private List<Integer> informationScores = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "evaluation_general_score", joinColumns = @JoinColumn(name = "evaluation_id"))
    @Column(name = "score")
    private List<Integer> generalScores = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "evaluation_delivery_score", joinColumns = @JoinColumn(name = "evaluation_id"))
    @Column(name = "score")
    private List<Integer> deliveryScores = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "evaluation_quality_score", joinColumns = @JoinColumn(name = "evaluation_id"))
    @Column(name = "score")
    private List<Integer> qualityScores = new ArrayList<>(); // Almacenará 2 valores, del 0-4 (puntuaciones)
    public String evaluationDateToString(){
        return String.valueOf(this.evaluationDate);
    }
    public String nextEvaluationToString(){
        return this.nextEvaluation.toString();
    }
}

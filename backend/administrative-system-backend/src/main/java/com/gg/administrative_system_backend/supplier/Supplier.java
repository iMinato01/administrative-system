package com.gg.administrative_system_backend.supplier;

import com.gg.administrative_system_backend.evaluation.Evaluation;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Builder.Default
    private boolean status = true;
    private String rfc;
    private String email;
    private String phoneNumber;
    private String services;
    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Evaluation> evaluations = new ArrayList<>();

    public void addEvaluation(Evaluation evaluation){
        this.evaluations.add(evaluation);
    }
}

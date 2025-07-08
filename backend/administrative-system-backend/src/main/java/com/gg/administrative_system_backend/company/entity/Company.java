package com.gg.administrative_system_backend.company.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Builder.Default
    private boolean status = true;
    private String rfc;
    private String phoneNumber;
    private String state;
    private String municipality;
    private String locality;
    private String postalCode;
    private String street;
    private String interiorNumber;
    private String exteriorNumber;
}

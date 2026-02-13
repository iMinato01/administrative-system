package com.gg.administrative_system_backend.company.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String serie;
    private Long fol;
    private boolean status;
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

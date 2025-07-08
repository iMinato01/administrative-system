package com.gg.administrative_system_backend.company.dto;

import lombok.Getter;

@Getter
public class UpdateCompanyDTO {
    private String name;
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

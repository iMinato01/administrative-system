package com.gg.administrative_system_backend.company.controller;

import com.gg.administrative_system_backend.company.repository.CompanyRepository;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.company.dto.CreateCompanyDTO;
import com.gg.administrative_system_backend.company.dto.UpdateCompanyDTO;
import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyRepository.findAll()));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Company>> saveCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO){
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyService.saveCompany(createCompanyDTO.getName(),
                createCompanyDTO.getRfc(), createCompanyDTO.getPhoneNumber(), createCompanyDTO.getState(), createCompanyDTO.getMunicipality(), createCompanyDTO.getLocality(),
                createCompanyDTO.getPostalCode(), createCompanyDTO.getStreet(), createCompanyDTO.getInteriorNumber(), createCompanyDTO.getExteriorNumber())));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(@Valid @RequestBody UpdateCompanyDTO updateCompanyDTO, @PathVariable Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                companyService.updateCompany(id, updateCompanyDTO.getName(), updateCompanyDTO.getStatus(), updateCompanyDTO.getRfc(),
                        updateCompanyDTO.getPhoneNumber(), updateCompanyDTO.getState(), updateCompanyDTO.getMunicipality(), updateCompanyDTO.getLocality(),
                        updateCompanyDTO.getPostalCode(), updateCompanyDTO.getStreet(), updateCompanyDTO.getExteriorNumber(), updateCompanyDTO.getExteriorNumber())));
    }
}

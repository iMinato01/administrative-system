package com.gg.administrative_system_backend.company.controller;

import com.gg.administrative_system_backend.company.dto.CompanyResponseDTO;
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

/**
 * REST controller responsible for managing company-related operations.
 * Provides endpoints for creating, updating and retrieving companies.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    /**
     * Creates a new company.
     *
     * @param createCompanyDTO the data required to create a company
     * @return an {@link ApiResponse} containing a confirmation message
     */
    @PostMapping
    public ResponseEntity<ApiResponse<String>> saveCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO) {
        return ResponseEntity
                .status(201)
                .body(ApiResponse.of(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        companyService.saveCompany(createCompanyDTO)));
    }

    /**
     * Updates an existing company by its identifier.
     *
     * @param updateCompanyDTO the updated company data
     * @param id the identifier of the company to update
     * @return an {@link ApiResponse} containing a confirmation message
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> updateCompany(@Valid @RequestBody UpdateCompanyDTO updateCompanyDTO, @PathVariable Long id){
        return ResponseEntity
                .status(200)
                .body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyService.updateCompany(id, updateCompanyDTO)));
    }

    /**
     * Retrieves all registered companies.
     *
     * @return an {@link ApiResponse} containing a list of {@link CompanyResponseDTO}
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyResponseDTO>>> filterAll(){
        return ResponseEntity
                .status(200)
                .body(ApiResponse.of(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        companyService.findAll()));
    }

    /**
     * Retrieves a company by its identifier.
     *
     * @param id the company identifier
     * @return an {@link ApiResponse} containing the requested {@link Company}
     */
    @GetMapping("/search/id")
    public ResponseEntity<ApiResponse<Company>> filterById(@RequestParam Long id){
        return ResponseEntity
                .status(200)
                .body(ApiResponse.of(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        companyService.findCompany(id)));
    }

    /**
     * Searches companies by a given value (e.g., name or other searchable fields).
     *
     * @param value the search value
     * @return an {@link ApiResponse} containing a list of matching {@link Company}
     */
    @GetMapping("/search/value")
    public ResponseEntity<ApiResponse<List<Company>>> filterAllByValue(@RequestParam(name = "v") String value){
        return ResponseEntity
                .status(200)
                .body(ApiResponse.of(
                        HttpStatus.OK.value(),
                        HttpStatus.OK.getReasonPhrase(),
                        companyService.findByValue(value)));
    }
}

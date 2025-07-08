package com.gg.administrative_system_backend.company.controller;

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
    @PostMapping
    public ResponseEntity<ApiResponse<Company>> saveCompany(@Valid @RequestBody CreateCompanyDTO createCompanyDTO){
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyService.saveCompany(createCompanyDTO)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Company>> updateCompany(@Valid @RequestBody UpdateCompanyDTO updateCompanyDTO, @PathVariable Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(),
                companyService.updateCompany(id, updateCompanyDTO)));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Company>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyService.findAll()));
    }
    @GetMapping("/search/id")
    public ResponseEntity<ApiResponse<Company>> filterById(@RequestParam Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(), companyService.findCompany(id)));
    }
    @GetMapping("/search/value")
    public ResponseEntity<ApiResponse<List<Company>>> filterAllByValue(@RequestParam(name = "v") String value){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(),HttpStatus.OK.getReasonPhrase(), companyService.findByValue(value)));
    }
}

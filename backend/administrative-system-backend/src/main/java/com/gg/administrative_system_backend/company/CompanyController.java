package com.gg.administrative_system_backend.company;

import com.gg.administrative_system_backend.response.ApiResponse;
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
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), companyService.saveCompany(createCompanyDTO.getName(),
                createCompanyDTO.getRfc(), createCompanyDTO.getPhoneNumber(), createCompanyDTO.getState(), createCompanyDTO.getMunicipality(), createCompanyDTO.getLocality(),
                createCompanyDTO.getPostalCode(), createCompanyDTO.getStreet(), createCompanyDTO.getInteriorNumber(), createCompanyDTO.getExteriorNumber())));
    }
}

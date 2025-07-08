package com.gg.administrative_system_backend.contract.controller;

import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.contract.dto.CreateContractDTO;
import com.gg.administrative_system_backend.contract.dto.UpdateContractDTO;
import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveContract(@RequestBody @Valid CreateContractDTO createContractDTO){
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), contractService.saveContract(createContractDTO)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(@PathVariable Long id, @RequestBody UpdateContractDTO updateContractDTO){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), contractService.updateContract(id, updateContractDTO)));
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contract>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), contractService.findAll()));
    }
    @GetMapping("/search/id")
    public ResponseEntity<ApiResponse<Contract>> filterById(@RequestParam Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), contractService.findContract(id)));
    }
    @GetMapping("/search/value")
    public ResponseEntity<ApiResponse<List<Contract>>> filterAllByValue(@RequestParam String value){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), contractService.findByValue(value)));
    }
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> filterAllByName(@RequestParam Boolean status){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), contractService.findNamesByStatus(status)));
    }
}

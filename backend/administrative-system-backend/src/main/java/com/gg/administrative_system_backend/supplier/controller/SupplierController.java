package com.gg.administrative_system_backend.supplier.controller;

import com.gg.administrative_system_backend.response.success.ApiResponse;
import com.gg.administrative_system_backend.supplier.dto.CreateSupplierDTO;
import com.gg.administrative_system_backend.supplier.dto.UpdateSupplierDTO;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.repository.SupplierRepository;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@AllArgsConstructor
public class SupplierController {
    private final SupplierRepository supplierRepository;
    private final SupplierService supplierService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Supplier>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), supplierRepository.findAll()));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveSupplier(@RequestBody @Valid CreateSupplierDTO createSupplierDTO){
        Supplier supplier = supplierService.saveSupplier(createSupplierDTO);
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), supplier));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Supplier>>> filterAllByValue(@RequestParam String filter){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), supplierService.findByValue(filter)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> updateSupplier(@RequestBody @Valid UpdateSupplierDTO updateSupplierDTO, @PathVariable Long id){
        Supplier supplier = supplierService.updateSupplier(id, updateSupplierDTO);
                return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), supplier));
    }
}

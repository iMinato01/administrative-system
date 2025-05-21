package com.gg.administrative_system_backend.supplier;

import com.gg.administrative_system_backend.response.ApiResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
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
        Supplier supplier = supplierService.saveSupplier(createSupplierDTO.getName(), createSupplierDTO.getRfc(),
                createSupplierDTO.getEmail(),createSupplierDTO.getPhoneNumber() ,createSupplierDTO.getServices());
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), supplier));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Supplier>> updateSupplier(@RequestBody @Valid UpdateSupplierDTO updateSupplierDTO, @PathVariable Long id){
        Supplier supplier = supplierService.updateSupplier(id, updateSupplierDTO.getName(), updateSupplierDTO.getStatus(), updateSupplierDTO.getRfc(),
                updateSupplierDTO.getEmail(), updateSupplierDTO.getPhoneNumber(), updateSupplierDTO.getServices());
                return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), supplier));
    }
}

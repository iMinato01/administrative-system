package com.gg.administrative_system_backend.contract.controller;

import com.gg.administrative_system_backend.contract.repository.ContractRepository;
import com.gg.administrative_system_backend.contract.service.ContractService;
import com.gg.administrative_system_backend.contract.dto.CreateContractDTO;
import com.gg.administrative_system_backend.contract.dto.UpdateContractDTO;
import com.gg.administrative_system_backend.contract.entity.Contract;
import com.gg.administrative_system_backend.contract.message.ContractMessage;
import com.gg.administrative_system_backend.shared.message.GeneralMessage;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
@AllArgsConstructor
public class ContractController {
    private final ContractRepository contractRepository;
    private final ContractService contractService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contract>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(200, ContractMessage.LIST_ALL.getMessage(), contractRepository.findAll()));
    }
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> filterByName(@RequestParam Boolean status){
        return ResponseEntity.status(200).body(ApiResponse.of(200, ContractMessage.LIST_NAMES.format((status)? GeneralMessage.STATUS_ACTIVE.getMessage():GeneralMessage.STATUS_INACTIVE.getMessage()), contractRepository.findOnlyNames(status)));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Contract>>> filterAllByValue(@RequestParam String filter){
        return ResponseEntity.status(200).body(ApiResponse.of(200, ContractMessage.LIST_BY_VALUE.format(filter), contractService.findByValue(filter)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveContract(@RequestBody @Valid CreateContractDTO createContractDTO){
        Contract contract = contractService.saveContract(createContractDTO.getName());
            return ResponseEntity.status(201).body(ApiResponse.of(201, ContractMessage.CREATED_SUCCESS.format(createContractDTO.getName()), contract));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(@RequestBody UpdateContractDTO updateContractDTO, @PathVariable Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(200, ContractMessage.UPDATED_SUCCESS.format(updateContractDTO.getName()), contractService.updateContract(id, updateContractDTO.getName(), updateContractDTO.getStatus(), updateContractDTO.getTotalExpenses())));
    }
}

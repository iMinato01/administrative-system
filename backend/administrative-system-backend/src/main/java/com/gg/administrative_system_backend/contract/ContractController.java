package com.gg.administrative_system_backend.contract;

import com.gg.administrative_system_backend.message.ContractMessage;
import com.gg.administrative_system_backend.message.GeneralMessage;
import com.gg.administrative_system_backend.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contracts")
public class ContractController {
    private final ContractRepository contractRepository;
    private final ContractService contractService;
    public ContractController(ContractRepository contractRepository, ContractService contractService){
        this.contractRepository = contractRepository;
        this.contractService = contractService;
    }
    @GetMapping
    public ResponseEntity<ApiResponse<List<Contract>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(200, ContractMessage.LIST_ALL.getMessage(), contractRepository.findAll()));
    }
    @GetMapping("/names")
    public ResponseEntity<ApiResponse<List<String>>> filterByName(@RequestParam Boolean status){
        return ResponseEntity.status(200).body(ApiResponse.of(200, String.format(ContractMessage.LIST_NAMES.getMessage(), (status)? GeneralMessage.STATUS_ACTIVE.getMessage():GeneralMessage.STATUS_INACTIVE.getMessage()), contractRepository.findOnlyNames(status)));
    }
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Contract>>> filterAllByValue(@RequestParam String value){
        return ResponseEntity.status(200).body(ApiResponse.of(200, String.format(ContractMessage.LIST_BY_VALUE.getMessage(), value), contractRepository.findByValue(value)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveContract(@RequestBody @Valid CreateContractDTO createContractDTO){
        Contract contract = contractService.saveContract(createContractDTO.getName());
            return ResponseEntity.status(201).body(ApiResponse.of(201, String.format(ContractMessage.CREATED_SUCCESS.getMessage(), createContractDTO.getName()), contract));
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateContract(@RequestBody @Valid UpdateContractDTO updateContractDTO, @PathVariable long id){
        return ResponseEntity.status(200).body(ApiResponse.of(200, String.format(ContractMessage.UPDATED_SUCCESS.getMessage(), updateContractDTO.getName()), contractService.updateContract(id, updateContractDTO.getName(), updateContractDTO.getStatus(), updateContractDTO.getTotalExpenses())));
    }
}

package com.gg.administrative_system_backend.contract;

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
    public ResponseEntity<List<Contract>> filterAll(){
        return ResponseEntity.status(200).body(contractRepository.findAll());
    }
    @GetMapping("/names")
    public ResponseEntity<List<String>> filterActives(@RequestParam Boolean status){
        return ResponseEntity.status(200).body(contractRepository.findOnlyNames(status));
    }
    @GetMapping("/search")
    public ResponseEntity<List<Contract>> filterAllByValue(@RequestParam String value){
        return ResponseEntity.status(200).body(contractRepository.findByValue(value));
    }
    @PostMapping
    public ResponseEntity<?> saveContract(@RequestBody @Valid CreateContractDTO createContractDTO){
        Contract contract = contractService.saveContract(createContractDTO.getName());
        return (contract != null) ? ResponseEntity.status(201).body(contract) : ResponseEntity.status(409).body("No se pudo guardar el contrato " + createContractDTO.getName());
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateContract(@RequestBody @Valid UpdateContractDTO updateContractDTO, @PathVariable long id){
        return ResponseEntity.status(200).body(contractService.updateContract(id, updateContractDTO.getName(), updateContractDTO.isStatus(), updateContractDTO.getTotalExpenses()));
    }
}

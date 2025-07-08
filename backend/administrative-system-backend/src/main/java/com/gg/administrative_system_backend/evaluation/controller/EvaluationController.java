package com.gg.administrative_system_backend.evaluation.controller;

import com.gg.administrative_system_backend.evaluation.repository.EvaluationRepository;
import com.gg.administrative_system_backend.evaluation.service.EvaluationService;
import com.gg.administrative_system_backend.evaluation.dto.CreateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.dto.UpdateEvaluationDTO;
import com.gg.administrative_system_backend.evaluation.entity.Evaluation;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/evaluations")
@AllArgsConstructor
public class EvaluationController {
    private final EvaluationRepository evaluationRepository;
    private final EvaluationService evaluationService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<Evaluation>>> filterAll(){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationRepository.findAll()));
    }
    @GetMapping("/search/id")
    public ResponseEntity<ApiResponse<List<Evaluation>>> filterBySupplier(@RequestParam Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationRepository.findBySupplierId(id)));

    }
    @GetMapping("/search/value")
    public ResponseEntity<ApiResponse<List<Evaluation>>> filterByValue(@RequestParam String value){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationService.findByValue(value)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveEvaluation(@RequestBody @Valid CreateEvaluationDTO createEvaluationDTO){
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(),
                evaluationService.saveEvaluation(createEvaluationDTO)));
    }
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<?>> updateEvaluation(@RequestBody UpdateEvaluationDTO updateEvaluationDTO, @PathVariable Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationService.updateEvaluation(id, updateEvaluationDTO)));
    }
}

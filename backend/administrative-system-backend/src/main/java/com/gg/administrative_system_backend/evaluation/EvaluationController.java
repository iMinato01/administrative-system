package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.response.ApiResponse;
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
    @GetMapping("/search/bySupplier")
    public ResponseEntity<ApiResponse<List<Evaluation>>> filterBySupplier(@RequestParam Long id){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationService.findBySupplier(id)));

    }
    @GetMapping("/search/byValue")
    public ResponseEntity<ApiResponse<List<Evaluation>>> filterByValue(@RequestParam String value){
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), evaluationService.findByValue(value)));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<?>> saveEvaluation(@RequestBody @Valid CreateEvaluationDTO createEvaluationDTO){
        Evaluation evaluation = evaluationService.saveEvaluation(createEvaluationDTO.getSupplierId(), createEvaluationDTO.getEvaluationDate(),
                createEvaluationDTO.getNextEvaluation(), createEvaluationDTO.getInformationScores(), createEvaluationDTO.getGeneralScores(),
                createEvaluationDTO.getDeliveryScores(), createEvaluationDTO.getQualityScores());
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), evaluation));
    }
}

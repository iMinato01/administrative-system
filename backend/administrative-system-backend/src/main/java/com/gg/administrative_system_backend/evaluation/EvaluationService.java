package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.message.SupplierMessage;
import com.gg.administrative_system_backend.supplier.Supplier;
import com.gg.administrative_system_backend.supplier.SupplierRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final SupplierRepository supplierRepository;
    @Transactional
    public Evaluation saveEvaluation(long id, LocalDate evaluationDate, LocalDate nextEvaluation, List<Integer> information,
                                     List<Integer> general, List<Integer> delivery, List<Integer> quality){
        Supplier supplier = supplierRepository.findById(id).orElseThrow(()->new EntityNotFoundException(SupplierMessage.SUPPLIER_NOT_FOUND.format(id)));
        return evaluationRepository.save(Evaluation.builder()
                .id(id)
                .evaluationDate(evaluationDate)
                .nextEvaluation(nextEvaluation)
                .supplier(supplier)
                .informationScores(information)
                .generalScores(general)
                .deliveryScores(delivery)
                .qualityScores(quality)
                .build());
    }
}

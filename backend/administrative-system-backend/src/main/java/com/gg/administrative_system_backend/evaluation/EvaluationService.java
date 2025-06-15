package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.message.EvaluationMessage;
import com.gg.administrative_system_backend.message.SupplierMessage;
import com.gg.administrative_system_backend.supplier.Supplier;
import com.gg.administrative_system_backend.supplier.SupplierRepository;
import com.gg.administrative_system_backend.supplier.SupplierService;
import com.gg.administrative_system_backend.util.RegexPatterns;
import com.gg.administrative_system_backend.util.UpdateProperty;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class EvaluationService {
    private final EvaluationRepository evaluationRepository;
    private final UpdateProperty updateProperty;
    private final SupplierService supplierService;
    @Transactional
    public Evaluation saveEvaluation(Long id, LocalDate evaluationDate, LocalDate nextEvaluation, List<Integer> information,
                                     List<Integer> general, List<Integer> delivery, List<Integer> quality){
        Supplier supplier = supplierService.findSupplier(id);
        return evaluationRepository.save(Evaluation.builder()
                .evaluationDate(evaluationDate)
                .nextEvaluation(nextEvaluation)
                .supplier(supplier)
                .informationScores(information)
                .generalScores(general)
                .deliveryScores(delivery)
                .qualityScores(quality)
                .build());
    }
    @Transactional
    public Evaluation updateEvaluation(Long id, Long supplierId, LocalDate evaluationDate, LocalDate nextEvaluation,
                                       List<Integer> informationScores, List<Integer> generalScores, List<Integer> deliveryScores,
                                       List<Integer> qualityScores){
        Evaluation evaluation = findEvaluation(id);
        Supplier supplier = supplierService.findSupplier(supplierId);
        updateProperty.updateIfChanged(evaluation::getSupplier, supplier, evaluation::setSupplier);
        updateProperty.updateIfChanged(evaluation::getEvaluationDate, evaluationDate, evaluation::setEvaluationDate);
        updateProperty.updateIfChanged(evaluation::getNextEvaluation, nextEvaluation, evaluation::setNextEvaluation);
        updateProperty.updateIfChanged(evaluation::getInformationScores, informationScores, evaluation::setInformationScores);
        updateProperty.updateIfChanged(evaluation::getGeneralScores, generalScores, evaluation::setGeneralScores);
        updateProperty.updateIfChanged(evaluation::getDeliveryScores, deliveryScores, evaluation::setDeliveryScores);
        updateProperty.updateIfChanged(evaluation::getQualityScores, qualityScores, evaluation::setQualityScores);
        return evaluation;
    }

    public List<Evaluation> findByValue(String value){
        if(value.matches(RegexPatterns.DECIMAL)){
            if(value.matches(RegexPatterns.LONG)){
                return evaluationRepository.findBySupplierId(Long.parseLong(value));
            }
            else if(value.matches(RegexPatterns.DATE_FORMAT)){
                LocalDate date = LocalDate.parse(value);
                return evaluationRepository.findByDate(date);
            }
        }
            return evaluationRepository.findBySupplierName(value);
    }
    private Evaluation findEvaluation(Long id){
        return evaluationRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(EvaluationMessage.EVALUATION_NOT_FOUND.format(id)));
    }
}

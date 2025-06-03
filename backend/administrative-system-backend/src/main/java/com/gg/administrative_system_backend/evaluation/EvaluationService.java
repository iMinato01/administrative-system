package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.message.SupplierMessage;
import com.gg.administrative_system_backend.supplier.Supplier;
import com.gg.administrative_system_backend.supplier.SupplierRepository;
import com.gg.administrative_system_backend.util.RegexPatterns;
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
    public Evaluation saveEvaluation(Long id, LocalDate evaluationDate, LocalDate nextEvaluation, List<Integer> information,
                                     List<Integer> general, List<Integer> delivery, List<Integer> quality){
        Supplier supplier = findSupplier(id);
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
    public Evaluation updateEvaluation(){
        return null;
    }
    public List<Evaluation> findBySupplier(Long id){
        return findSupplier(id).getEvaluations();
    }
    //Pendiente por optimizar consultas de busqueda
    public List<Evaluation> findByValue(String value){
        if(value.matches(RegexPatterns.DECIMAL)){
            if(value.matches(RegexPatterns.LONG)){
                return findBySupplier(Long.parseLong(value));
            }
            else if(value.matches(RegexPatterns.DATE_FORMAT)){
                LocalDate date = LocalDate.parse(value);
                return evaluationRepository.findByDate(date);
            }
        }
            return evaluationRepository.findBySupplierName(value);
    }
    private Supplier findSupplier(Long id){
        return supplierRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(SupplierMessage.SUPPLIER_NOT_FOUND.format(id)));
    }
}

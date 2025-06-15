package com.gg.administrative_system_backend.evaluation;

import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.message.SupplierMessage;
import com.gg.administrative_system_backend.supplier.Supplier;
import com.gg.administrative_system_backend.supplier.SupplierRepository;
import com.gg.administrative_system_backend.supplier.SupplierService;
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
    /*
    * Actualización de las propiedades básicas, así como las de la relación a partir del ID
    * */
    public Evaluation updateEvaluation(){
        //Caso propiedades base
        //Caso relación
        return null;
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

package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.pettycash.expense.dto.CreateExpenseDTO;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility class containing generic methods for validations.
 */
@Component
@RequiredArgsConstructor
public class ValidationUtils {
    private final Validator validator;

    /**
     * Validates whether a property is already being used by another entity.
     * @param value     Input value to be validated.
     * @param validation Boolean supplier that checks if the property exists in the database.
     * @param exception  Exception to be thrown if the property is already in use.
     * @param <T>       Type of the value being validated.
     */
  public static <T> void validateIfExists(T value, Predicate<T> validation, Supplier<? extends RuntimeException> exception){
      if(validation.test(value)){
          throw exception.get();
      }
  }

  public <T> void validateFields(T dto){
      Set<ConstraintViolation<T>> constraint = validator.validate(dto);
      if(!constraint.isEmpty()){
          throw new ConstraintViolationException(constraint);
      }
  }
}

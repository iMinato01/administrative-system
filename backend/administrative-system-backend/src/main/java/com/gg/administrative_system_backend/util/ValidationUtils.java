package com.gg.administrative_system_backend.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Utility class containing generic methods for validations.
 */
@Component
@NoArgsConstructor
public class ValidationUtils {

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
}

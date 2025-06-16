package com.gg.administrative_system_backend.util;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.function.Supplier;
@Component
@NoArgsConstructor
public class ValidationUtils {
  public <T> void validateIfExists(T value, Predicate<T> validation, Supplier<? extends RuntimeException> exception){
      if(validation.test(value)){
          throw exception.get();
      }
  }
}

package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Utility class containing generic methods for entity updates.
 */
@Component
@NoArgsConstructor
public class UpdateUtils {

    /**
     * Updates an entity's property only if the new value is not null and is different from the current one.
     * @param entityGetter   Supplier for the current value from the entity.
     * @param getter Supplier for the new value from the DTO.
     * @param setter   Consumer that sets the new value on the entity.
     * @param <T>      Type of the value to compare and update.
     */
    public static <T> void updateIfChanged(Supplier<T> getter, Supplier<T> entityGetter, Consumer<T> setter) {
        T value = getter.get();
        T currentValue = entityGetter.get();
        if (value instanceof String && ((String) value).isBlank()) {
            throw new ValueRequiredException(ExceptionMessage.VALUE_REQUIRED.getMessage());
        }
        if (value != null && !Objects.equals(currentValue, value)) {
            setter.accept(value);
        }
    }

    public static <T, R> void updateIfChanged(Supplier<T> currentId, Supplier<T> newId, Function<T, R> finder, Consumer<R> consumer){
        if(newId.get()!= null && !Objects.equals(currentId.get(), newId.get())){
            R newRelation = finder.apply(newId.get());
            consumer.accept(newRelation);
        }
    }
}

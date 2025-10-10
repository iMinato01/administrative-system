package com.gg.administrative_system_backend.util;

import com.gg.administrative_system_backend.exception.ValueRequiredException;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Utility class containing generic methods for entity updates.
 */
@Component
@NoArgsConstructor
public class UpdateUtils {

    /**
     * Updates an entity's property only if the new value is not null and is different from the current one.
     * @param getter   Supplier for the current value from the entity.
     * @param newValue Supplier for the new value from the DTO.
     * @param setter   Consumer that sets the new value on the entity.
     * @param <T>      Type of the value to compare and update.
     */
    public static <T> void updateIfChanged(Supplier<T> getter, Supplier<T> newValue, Consumer<T> setter) {
        T value = newValue.get();
        if (value instanceof String && ((String) value).isBlank()) {
            throw new ValueRequiredException(GenericMessage.VALUE_REQUIRED.getMessage());
        }
        if (value != null && !Objects.equals(getter.get(), value)) {
            setter.accept(value);
        }
    }
}

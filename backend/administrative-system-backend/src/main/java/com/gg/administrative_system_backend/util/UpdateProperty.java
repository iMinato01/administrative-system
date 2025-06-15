package com.gg.administrative_system_backend.util;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class UpdateProperty {
    public <T> void updateIfChanged(Supplier<T> getter, T newValue, Consumer<T> setter){
        if(newValue != null && !Objects.equals(getter.get(), newValue)){
            setter.accept(newValue);
        }
    }
}

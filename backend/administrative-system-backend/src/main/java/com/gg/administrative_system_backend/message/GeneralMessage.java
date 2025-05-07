package com.gg.administrative_system_backend.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GeneralMessage {
    STATUS_ACTIVE("activos"),
    STATUS_INACTIVE("inactivos");
    private final String message;
}

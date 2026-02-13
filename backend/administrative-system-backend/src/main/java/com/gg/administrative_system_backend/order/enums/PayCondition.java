package com.gg.administrative_system_backend.order.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PayCondition {
    PUE("PUE", "PAGO EN UNA SOLA EXHIBICIÓN"),
    PPD("PPD", "PAGO EN PARCIALIDADES O DIFERIDO");
    private final String code;
    private final String description;
}

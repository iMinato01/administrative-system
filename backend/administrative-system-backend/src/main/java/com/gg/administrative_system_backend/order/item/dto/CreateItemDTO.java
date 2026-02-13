package com.gg.administrative_system_backend.order.item.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
public class CreateItemDTO {
    private String sat;
    @NotBlank
    private String description;
    @NotBlank
    private String unity;
    @NotNull
    private BigDecimal quantity;
    @NotNull
    private BigDecimal price;
}

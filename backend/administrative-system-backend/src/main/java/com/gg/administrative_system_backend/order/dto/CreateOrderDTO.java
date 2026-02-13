package com.gg.administrative_system_backend.order.dto;

import com.gg.administrative_system_backend.order.enums.CfdiUsage;
import com.gg.administrative_system_backend.order.enums.PayCondition;
import com.gg.administrative_system_backend.order.enums.PayMethod;
import com.gg.administrative_system_backend.order.item.dto.CreateItemDTO;
import com.gg.administrative_system_backend.order.item.entity.Item;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class CreateOrderDTO {
    @NotNull
    private PayMethod payMethod;
    @NotNull
    private CfdiUsage cfdiUsage;
    @NotNull
    private PayCondition payCondition;
    @NotBlank
    private String reference;
    @NotEmpty
    @Valid
    private List<CreateItemDTO> items;
    @NotBlank
    private String eta;
    @NotBlank
    private String bank;
    @NotBlank
    private String account;
    private String clabe;
    private BigDecimal iva;
    private BigDecimal isr;
    @NotNull
    private Long supplierId;
    @NotNull
    private Long companyId;
}

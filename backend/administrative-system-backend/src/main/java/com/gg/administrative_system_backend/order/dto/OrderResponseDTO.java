package com.gg.administrative_system_backend.order.dto;

import com.gg.administrative_system_backend.order.item.entity.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Builder
@Getter
@Setter
public class OrderResponseDTO {
    private Long id;
    private boolean status;
    private String serie;
    private Long fol;
    private LocalDate date;
    private String payMethod;
    private String cfdiUsage;
    private String payCondition;
    private String reference;
    private List<Item> items;
    private String eta;
    private String bank;
    private String account;
    private String clabe;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal isr;
    private BigDecimal total;
    private String supplier;
    private String company;
}

package com.gg.administrative_system_backend.order.item.mapper;

import com.gg.administrative_system_backend.order.item.dto.CreateItemDTO;
import com.gg.administrative_system_backend.order.item.entity.Item;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Getter
public class ItemMapper {
    public Item toEntity(CreateItemDTO createItemDTO){
        Item newItem =Item.builder()
                .sat(createItemDTO.getSat())
                .description(createItemDTO.getDescription())
                .unity(createItemDTO.getUnity())
                .quantity(createItemDTO.getQuantity())
                .price(createItemDTO.getPrice())
                .build();
        newItem.calculateTotal();
        return newItem;
    }
}

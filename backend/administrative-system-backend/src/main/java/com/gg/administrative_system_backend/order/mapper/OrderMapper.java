package com.gg.administrative_system_backend.order.mapper;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.order.dto.CreateOrderDTO;
import com.gg.administrative_system_backend.order.dto.OrderResponseDTO;
import com.gg.administrative_system_backend.order.entity.Order;
import com.gg.administrative_system_backend.order.item.mapper.ItemMapper;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
@AllArgsConstructor
public class OrderMapper {
    private final ItemMapper itemMapper;
    public Order toEntity(CreateOrderDTO createOrderDTO, Supplier supplier, Company company) {
        Order newOrder =
                Order.builder()
                        .supplier(supplier)
                        .company(company)
                        .fol(company.getFol())
                        .serie(company.getSerie())
                        .payMethod(createOrderDTO.getPayMethod())
                        .cfdiUsage(createOrderDTO.getCfdiUsage())
                        .payCondition(createOrderDTO.getPayCondition())
                        .reference(createOrderDTO.getReference())
                        .eta(createOrderDTO.getEta())
                        .bank(createOrderDTO.getBank())
                        .account(createOrderDTO.getAccount())
                        .clabe(createOrderDTO.getClabe())
                        .iva(createOrderDTO.getIva())
                        .isr(createOrderDTO.getIsr())
                        .build();
        newOrder.setItems(createOrderDTO.getItems().stream().map(itemMapper::toEntity).peek(item -> item.setOrder(newOrder)).toList());
        return newOrder;
    }

    public OrderResponseDTO toResponse(Order order){
        return OrderResponseDTO.builder()
                .id(order.getId())
                .status(order.isStatus())
                .fol(order.getFol())
                .serie(order.getSerie())
                .date(order.getDate())
                .payMethod(order.getPayMethod().name())
                .cfdiUsage(order.getCfdiUsage().name())
                .payCondition(order.getPayCondition().name())
                .reference(order.getReference())
                .eta(order.getEta())
                .bank(order.getBank())
                .account(order.getAccount())
                .clabe(order.getClabe())
                .subtotal(order.getSubtotal())
                .items(order.getItems())
                .iva(order.getIva())
                .isr(order.getIsr())
                .total(order.getTotal())
                .supplier(order.getSupplier().getName())
                .company(order.getCompany().getName())
                .items(order.getItems())
                .build();
    }
}

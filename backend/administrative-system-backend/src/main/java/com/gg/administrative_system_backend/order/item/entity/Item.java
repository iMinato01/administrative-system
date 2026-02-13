package com.gg.administrative_system_backend.order.item.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.gg.administrative_system_backend.order.entity.Order;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String sat;
    private String description;
    private String unity;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal total;
    @ManyToOne(fetch = FetchType.LAZY,  cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    @JsonBackReference
    private Order order;
    public void calculateTotal(){
        this.total = this.quantity.multiply(this.price);
    }
}


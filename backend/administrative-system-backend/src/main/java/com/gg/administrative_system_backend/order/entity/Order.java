package com.gg.administrative_system_backend.order.entity;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.order.enums.CfdiUsage;
import com.gg.administrative_system_backend.order.enums.PayCondition;
import com.gg.administrative_system_backend.order.enums.PayMethod;
import com.gg.administrative_system_backend.order.item.entity.Item;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "orders")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Builder.Default
    private boolean status = true;
    @Builder.Default
    private LocalDate date =  LocalDate.now();
    private Long fol;
    private String serie;
    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;
    @Enumerated(EnumType.STRING)
    private CfdiUsage cfdiUsage;
    @Enumerated(EnumType.STRING)
    private PayCondition payCondition;
    private String reference;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Item> items;
    private String eta;
    private String bank;
    private String account;
    private String clabe;
    private BigDecimal subtotal;
    private BigDecimal iva;
    private BigDecimal isr;
    private BigDecimal total;
    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    public void calculateSubtotal(){
        this.subtotal = BigDecimal.ZERO;
        this.items.forEach(item -> {
           this.subtotal = this.subtotal.add(item.getTotal());
        });
    }

    public void calculateTotal(){
        this.total = BigDecimal.ZERO;
        if(subtotal.compareTo(BigDecimal.ZERO) != 0){
            this.total = subtotal.add(this.iva != null? this.iva: BigDecimal.ZERO).subtract(this.isr != null? this.isr: BigDecimal.ZERO);
        }
    }
}

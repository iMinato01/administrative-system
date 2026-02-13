package com.gg.administrative_system_backend.order.service;

import com.gg.administrative_system_backend.company.entity.Company;
import com.gg.administrative_system_backend.company.service.CompanyService;
import com.gg.administrative_system_backend.exception.EntityNotFoundException;
import com.gg.administrative_system_backend.order.dto.CreateOrderDTO;
import com.gg.administrative_system_backend.order.dto.OrderResponseDTO;
import com.gg.administrative_system_backend.order.entity.Order;
import com.gg.administrative_system_backend.order.mapper.OrderMapper;
import com.gg.administrative_system_backend.order.repository.OrderRepository;
import com.gg.administrative_system_backend.shared.message.ExceptionMessage;
import com.gg.administrative_system_backend.shared.message.GenericMessage;
import com.gg.administrative_system_backend.shared.report.ReportRoute;
import com.gg.administrative_system_backend.supplier.entity.Supplier;
import com.gg.administrative_system_backend.supplier.service.SupplierService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final SupplierService supplierService;
    private final CompanyService companyService;
    private final OrderMapper  orderMapper;

    @Transactional
    public List<OrderResponseDTO> findAll(){
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(orderMapper::toResponse).toList();
    }

    @Transactional
    public String saveOrder(CreateOrderDTO createOrderDTO) {
        Supplier supplier = supplierService.findSupplier(createOrderDTO.getSupplierId());
        Company company = companyService.findCompany(createOrderDTO.getCompanyId());
        Order newOrder = orderMapper.toEntity(createOrderDTO, supplier, company);
        newOrder.calculateSubtotal();
        newOrder.calculateTotal();
        company.increaseFol();
        orderRepository.save(newOrder);
        return GenericMessage.SAVED.getMessage();
    }

    public Order findOrder(Long id){
        return orderRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(ExceptionMessage.ENTITY_NOT_FOUND.format(ReportRoute.ORDER.getName(), id)));
    }
}

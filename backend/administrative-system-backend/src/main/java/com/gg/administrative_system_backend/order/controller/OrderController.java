package com.gg.administrative_system_backend.order.controller;

import com.gg.administrative_system_backend.order.dto.CreateOrderDTO;
import com.gg.administrative_system_backend.order.dto.OrderResponseDTO;
import com.gg.administrative_system_backend.order.service.OrderService;
import com.gg.administrative_system_backend.response.success.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponseDTO>>> filterAll() {
        return ResponseEntity.status(200).body(ApiResponse.of(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), orderService.findAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<String>> saveOrder(@RequestBody CreateOrderDTO createOrderDTO) {
        return ResponseEntity.status(201).body(ApiResponse.of(HttpStatus.CREATED.value(), HttpStatus.CREATED.getReasonPhrase(), orderService.saveOrder(createOrderDTO)));
    }
}

package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateOrderDto;
import com.example.rd_log_api.domain.dto.UpdateOrderStatusDto;
import com.example.rd_log_api.domain.dto.requests.OrderCreationRequest;
import com.example.rd_log_api.service.OrdersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService service;

    @GetMapping
    public ResponseEntity<List<OrdersDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {
        OrdersDto createdOrdersDto = service.createOrder(orderCreationRequest);
        return ResponseEntity.ok().body(createdOrdersDto);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdersDto> updateOrderStatus(@PathVariable Long id,
                                                       @RequestBody @Valid UpdateOrderStatusDto updateStatusDto) {
        OrdersDto updateOrderDto = service.updateOrderStatus(id, updateStatusDto);
        return ResponseEntity.ok().body(updateOrderDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable Long id, @RequestBody @Valid UpdateOrderDto updateOrderDto) {
        OrdersDto updatedOrder = service.updateOrder(id, updateOrderDto);
        return ResponseEntity.ok().body(updatedOrder);
    }

}



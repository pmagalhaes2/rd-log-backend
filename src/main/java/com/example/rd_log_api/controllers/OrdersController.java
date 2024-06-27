package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateOrderDto;
import com.example.rd_log_api.domain.dto.UpdateOrderStatusDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.OrderCreationRequest;
import com.example.rd_log_api.domain.dto.responses.OrderResponse;
import com.example.rd_log_api.service.OrdersService;
import jakarta.transaction.Transactional;
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

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getById(@PathVariable Long id) throws NotFoundException {
        OrdersDto foundedOrder = service.getById(id);
        return ResponseEntity.ok().body(foundedOrder);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {
        OrderResponse createdOrdersDto = service.createOrder(orderCreationRequest);
        return ResponseEntity.ok().body(createdOrdersDto);
    }

    @Transactional
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrdersDto> updateOrderStatus(@PathVariable Long id,
                                                       @RequestBody @Valid UpdateOrderStatusDto updateStatusDto) throws NotFoundException {
        OrdersDto updateOrderDto = service.updateOrderStatus(id, updateStatusDto);
        return ResponseEntity.ok().body(updateOrderDto);
    }

    @Transactional
    @PatchMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrder(@PathVariable Long id,
                                                 @RequestBody @Valid UpdateOrderDto updateOrderDto) throws NotFoundException {
        OrdersDto updatedOrder = service.updateOrder(id, updateOrderDto);
        return ResponseEntity.ok().body(updatedOrder);
    }
}



package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateStatusDto;
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

    @PatchMapping("/{id}")
    public ResponseEntity<OrdersDto> updateOrderStatus(@PathVariable Long id,
                                                       @RequestBody @Valid UpdateStatusDto updateStatusDto) {
        OrdersDto updateOrderDto = service.updateOrderStatus(id, updateStatusDto);
        return ResponseEntity.ok().body(updateOrderDto);
    }
}


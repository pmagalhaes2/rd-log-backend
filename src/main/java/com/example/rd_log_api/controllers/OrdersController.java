package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.repositories.OrdersRepository;
import com.example.rd_log_api.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService service;

    @GetMapping
    public List<OrdersDto> getAll() {
        return service.getAll();
    };


}


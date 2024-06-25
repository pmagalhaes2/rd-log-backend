package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersRepository repository;
    @GetMapping
    public List<Orders> getAll() {
        return repository.findAll();
    };


}


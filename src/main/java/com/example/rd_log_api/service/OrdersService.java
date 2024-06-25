package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.domain.mappers.OrdersMapper;
import com.example.rd_log_api.repositories.AddressRepository;
import com.example.rd_log_api.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersService {

    @Autowired
    private OrdersRepository repository;

    @Autowired
    private AddressRepository addressRepository;

    public List<OrdersDto> getAll() {
         return repository.findAll().stream().map(OrdersMapper::toOrdersDto).toList();


    }

}

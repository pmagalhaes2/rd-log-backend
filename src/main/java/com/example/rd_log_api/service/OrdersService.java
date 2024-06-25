package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateStatusDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
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

    public OrdersDto getById(Long id) {
        return OrdersMapper.toOrdersDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(OrdersDto.class, String.valueOf(id))));
    }

    public OrdersDto updateOrderStatus(Long id, UpdateStatusDto updateStatusDto) {
        OrdersDto order = getById(id);
        order.setStatus(updateStatusDto.getStatus());
        return OrdersMapper.toOrdersDto(repository.save(OrdersMapper.toEntityFromDto(order)));
    }
}

package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.AdministratorDto;
import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateOrderDto;
import com.example.rd_log_api.domain.dto.UpdateOrderStatusDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.AdministratorCreationRequest;
import com.example.rd_log_api.domain.dto.requests.OrderCreationRequest;
import com.example.rd_log_api.domain.entities.Administrator;
import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.domain.mappers.AdministratorMapper;
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

    public OrdersDto updateOrderStatus(Long id, UpdateOrderStatusDto updateStatusDto) {
        OrdersDto order = getById(id);
        order.setStatus(updateStatusDto.getStatus());
        return OrdersMapper.toOrdersDto(repository.save(OrdersMapper.toEntityFromDto(order)));
    }

    public OrdersDto updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        OrdersDto order = getById(id);
        order.setStatus(updateOrderDto.getStatus());
        order.setLogistic_company_id(updateOrderDto.getLogistic_company_id());
        return OrdersMapper.toOrdersDto(repository.save(OrdersMapper.toEntityFromDto(order)));
    }

    public OrdersDto createOrder(OrderCreationRequest OrdersDto ) {
        Orders newOrder = OrdersMapper.toEntityFromDto(OrdersDto);
    }
}
//public AdministratorDto createAdministrator(AdministratorCreationRequest administrator) {
//    Administrator newAdministrator = AdministratorMapper.toEntityFromCreationRequest(administrator);
//    newAdministrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
//    Administrator savedAdministrator = repository.save(newAdministrator);
//    return AdministratorMapper.toAdministratorDto(savedAdministrator);
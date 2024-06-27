package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateOrderDto;
import com.example.rd_log_api.domain.dto.UpdateOrderStatusDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.OrderCreationRequest;
import com.example.rd_log_api.domain.dto.responses.OrderResponse;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.domain.mappers.OrdersMapper;
import com.example.rd_log_api.repositories.AddressRepository;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
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

    @Autowired
    private LogisticCompanyRepository logisticCompanyRepository;

    public List<OrdersDto> getAll() {
        return repository.findAll().stream().map(OrdersMapper::toOrdersDto).toList();
    }

    public OrdersDto getById(Long id) {
        return OrdersMapper.toOrdersDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(OrdersDto.class, String.valueOf(id))));
    }

    public OrdersDto updateOrderStatus(Long id, UpdateOrderStatusDto updateStatusDto) {
        Orders order = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Orders.class, String.valueOf(id)));
        order.setStatus(updateStatusDto.getStatus());
        return OrdersMapper.toOrdersDto(repository.save(order));
    }

    public OrdersDto updateOrder(Long id, UpdateOrderDto updateOrderDto) {
        Orders order = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Orders.class, String.valueOf(id)));
        order.setStatus(updateOrderDto.getStatus());

        if (updateOrderDto.getLogistic_company_id() != null) {
            LogisticCompany logisticCompany = logisticCompanyRepository.findById(updateOrderDto.getLogistic_company_id())
                    .orElseThrow(() -> new NotFoundException(LogisticCompany.class, String.valueOf(updateOrderDto.getLogistic_company_id())));
            order.setLogistic_company(logisticCompany);
        }

        return OrdersMapper.toOrdersDto(repository.save(order));
    }

    public OrderResponse createOrder(OrderCreationRequest orderCreationRequest) {
        Orders newOrder = OrdersMapper.toEntityFromCreationRequest(orderCreationRequest);
        newOrder.setStatus("Pendente");

        if (orderCreationRequest.getLogistic_company_id() != null) {
            LogisticCompany logisticCompany = logisticCompanyRepository.findById(orderCreationRequest.getLogistic_company_id())
                    .orElseThrow(() -> new NotFoundException(LogisticCompany.class, String.valueOf(orderCreationRequest.getLogistic_company_id())));
            newOrder.setLogistic_company(logisticCompany);
        }

        return OrdersMapper.toOrderResponse(repository.save(newOrder));
    }
}

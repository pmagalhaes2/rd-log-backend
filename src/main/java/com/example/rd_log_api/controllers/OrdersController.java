package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.dto.UpdateOrderDto;
import com.example.rd_log_api.domain.dto.UpdateOrderStatusDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.OrderCreationRequest;
import com.example.rd_log_api.domain.dto.responses.OrderResponse;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.Orders;
import com.example.rd_log_api.service.OrdersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @Operation(summary = "Create an order", description = "Created a new order.", tags = {"Order"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = @Content)
    })
    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderCreationRequest orderCreationRequest) {
        OrderResponse createdOrderResponse = service.createOrder(orderCreationRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdOrderResponse.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdOrderResponse);
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



package com.example.rd_log_api.repositories;

import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findOrdersByStatusInAndLogisticCompany(List<String> status, LogisticCompany logisticCompany);
}

package com.example.rd_log_api.repositories;

import com.example.rd_log_api.domain.entities.CustomLogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomLogisticCompanyRepository extends JpaRepository<CustomLogisticCompany, Long> {
    CustomLogisticCompany findFirstByOrderByName();
}
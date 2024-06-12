package com.example.rd_log_api.repositories;

import com.example.rd_log_api.domain.entities.LogisticCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticCompanyRepository extends JpaRepository<LogisticCompany, Long> {
    LogisticCompany findByEmail(String email);
    List<LogisticCompany> findByAddressState(String state);
}

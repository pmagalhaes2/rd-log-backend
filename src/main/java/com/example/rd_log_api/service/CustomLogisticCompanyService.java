package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.entities.CustomLogisticCompany;
import com.example.rd_log_api.repositories.CustomLogisticCompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomLogisticCompanyService {

    private final CustomLogisticCompanyRepository customLogisticCompanyRepository;

    public CustomLogisticCompanyService(CustomLogisticCompanyRepository customLogisticCompanyRepository) {
        this.customLogisticCompanyRepository = customLogisticCompanyRepository;
    }

    public CustomLogisticCompany getFirstCompany() {
        return customLogisticCompanyRepository.findFirstByOrderByName();
    }
}

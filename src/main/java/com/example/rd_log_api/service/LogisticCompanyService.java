package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.mappers.LogisticCompanyMapper;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogisticCompanyService {

    @Autowired
    private LogisticCompanyRepository repository;

    public LogisticCompanyCreationRequest createLogisticCompany(LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompany newLogisticCompany = LogisticCompanyMapper.toEntity(logisticCompany);
        repository.save(newLogisticCompany);
        return LogisticCompanyMapper.toDto(newLogisticCompany);
    }
}

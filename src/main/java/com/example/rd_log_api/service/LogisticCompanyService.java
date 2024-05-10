package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.mappers.LogisticCompanyMapper;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogisticCompanyService {

    @Autowired
    private LogisticCompanyRepository repository;

    public List<LogisticCompanyCreationRequest> getAll() {
        return repository.findAll().stream().map(LogisticCompanyMapper::toDto).toList();
    }

    public LogisticCompany getById(Long id){
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public LogisticCompanyCreationRequest createLogisticCompany(LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompany newLogisticCompany = LogisticCompanyMapper.toEntity(logisticCompany);
        repository.save(newLogisticCompany);
        return LogisticCompanyMapper.toDto(newLogisticCompany);
    }
}

package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyDto;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.mappers.LogisticCompanyMapper;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;

import java.util.List;

@Service
public class LogisticCompanyService {

    @Autowired
    private LogisticCompanyRepository repository;

    public List<LogisticCompanyDto> getAll() {
        return repository.findAll().stream().map(LogisticCompanyMapper::toLogisticDto).toList();
    }

    public LogisticCompanyDto getById(Long id) throws NotFoundException {
        return LogisticCompanyMapper.toLogisticDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LogisticCompanyDto.class, String.valueOf(id))));
    }

    public LogisticCompanyCreationRequest createLogisticCompany(LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompany newLogisticCompany = LogisticCompanyMapper.toEntityFromCreationRequest(logisticCompany);
        repository.save(newLogisticCompany);
        return LogisticCompanyMapper.toCreationRequestDto(newLogisticCompany);
    }

    public void deleteLogisticCompany(Long id) throws NotFoundException {
        LogisticCompanyDto foundedLogisticCompany = getById(id);
        repository.delete(LogisticCompanyMapper.toEntityFromLogisticDto(foundedLogisticCompany));
    }
}
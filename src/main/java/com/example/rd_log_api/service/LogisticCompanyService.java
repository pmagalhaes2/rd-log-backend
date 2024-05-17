package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyUpdateRequest;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.mappers.LogisticCompanyMapper;
import com.example.rd_log_api.repositories.LogisticCompanyRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;

import java.util.List;

@Service
public class LogisticCompanyService {

    private final LogisticCompanyRepository repository;

    @Autowired
    public LogisticCompanyService(LogisticCompanyRepository repository) {
        this.repository = repository;
    }

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


    @Transactional
    public LogisticCompanyDto updateLogisticCompany(Long id, LogisticCompanyUpdateRequest logisticCompany) throws NotFoundException {
        LogisticCompanyDto foundedLogisticCompany = getById(id);
        foundedLogisticCompany.setName(logisticCompany.getName());
        foundedLogisticCompany.setOpening_hours(logisticCompany.getOpening_hours());
        foundedLogisticCompany.setClosing_hours(logisticCompany.getClosing_hours());
        foundedLogisticCompany.setPhone_number(logisticCompany.getPhone_number());
        foundedLogisticCompany.setEmail(logisticCompany.getEmail());
        foundedLogisticCompany.setAccepts_dangerous_loads(
                logisticCompany.getAccepts_dangerous_loads() != null ?
                        logisticCompany.getAccepts_dangerous_loads() :
                        foundedLogisticCompany.getAccepts_dangerous_loads());

        return LogisticCompanyMapper.toLogisticDto(
                repository.save(LogisticCompanyMapper.toEntityFromLogisticDto(foundedLogisticCompany)));
    }

    @Transactional
    public void deleteLogisticCompany(Long id) throws NotFoundException {
        LogisticCompanyDto foundedLogisticCompany = getById(id);
        repository.delete(LogisticCompanyMapper.toEntityFromLogisticDto(foundedLogisticCompany));
    }
}
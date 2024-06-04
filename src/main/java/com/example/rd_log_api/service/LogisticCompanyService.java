package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LoginResponse;
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
        LogisticCompany existingLogisticCompany = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(LogisticCompanyDto.class, String.valueOf(id)));
        existingLogisticCompany.setName(logisticCompany.getName());
        existingLogisticCompany.setOpeningHours(logisticCompany.getOpening_hours());
        existingLogisticCompany.setClosingHours(logisticCompany.getClosing_hours());
        existingLogisticCompany.setPhoneNumber(logisticCompany.getPhone_number());
        existingLogisticCompany.setEmail(logisticCompany.getEmail());
        existingLogisticCompany.setPassword(logisticCompany.getPassword());
        LogisticCompany updatedLogisticCompany = repository.save(existingLogisticCompany);

        return LogisticCompanyMapper.toLogisticDto(updatedLogisticCompany);
    }

    @Transactional
    public void deleteLogisticCompany(Long id) throws NotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(LogisticCompany.class, String.valueOf(id));
        }
    }

    public LoginResponse login(LoginDto loginDto) {
        LogisticCompany logisticCompany = repository.findByEmail(loginDto.getEmail());
        if (logisticCompany != null && logisticCompany.getPassword() != null && logisticCompany.getPassword().equals(loginDto.getPassword())) {
            return new LoginResponse("Login realizado com sucesso.", logisticCompany.getId(), logisticCompany.getName());
        } else {
            throw new RuntimeException("Dados inválidos, tente novamente.");
        }
    }
}

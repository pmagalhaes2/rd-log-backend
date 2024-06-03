package com.example.rd_log_api.service;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.requests.AdministratorCreationRequest;
import com.example.rd_log_api.domain.dto.requests.AdministratorDto;
import com.example.rd_log_api.domain.dto.requests.AdministratorUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LoginAdminResponse;
import com.example.rd_log_api.domain.dto.responses.LoginResponse;
import com.example.rd_log_api.domain.entities.Administrator;
import com.example.rd_log_api.domain.mappers.AdministratorMapper;
import com.example.rd_log_api.repositories.AdministratorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;

import java.util.List;

@Service
public class AdministratorService {
    private final AdministratorRepository repository;

    @Autowired
    public AdministratorService(AdministratorRepository repository) {
        this.repository = repository;
    }

    public List<AdministratorDto> getAll() {
        return repository.findAll().stream().map(AdministratorMapper::toAdministratorDto).toList();
    }

    public AdministratorDto getById(Long id) throws NotFoundException {
        return AdministratorMapper.toAdministratorDto(repository.findById(id)
                .orElseThrow(() -> new NotFoundException(AdministratorDto.class, String.valueOf(id))));
    }

    public AdministratorDto createAdministrator(AdministratorCreationRequest administrator) {
        Administrator newAdministrator = AdministratorMapper.toEntityFromCreationRequest(administrator);
        Administrator savedAdministrator = repository.save(newAdministrator);
        return AdministratorMapper.toAdministratorDto(savedAdministrator);
    }


    @Transactional
    public AdministratorDto updateAdministrator(Long id, AdministratorUpdateRequest administratorUpdateRequest) throws NotFoundException {
        Administrator existingAdministrator = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(AdministratorDto.class, String.valueOf(id)));
        existingAdministrator.setName(administratorUpdateRequest.getName());
        existingAdministrator.setCpf(administratorUpdateRequest.getCpf());
        existingAdministrator.setEmail(administratorUpdateRequest.getEmail());
        if (administratorUpdateRequest.getPassword() != null) {
            existingAdministrator.setPassword(administratorUpdateRequest.getPassword());
        }
        Administrator updatedAdministrator = repository.save(existingAdministrator);

        return AdministratorMapper.toAdministratorDto(updatedAdministrator);
    }

    @Transactional
    public void deleteAdministrator(Long id) throws NotFoundException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new NotFoundException(Administrator.class, String.valueOf(id));
        }
    }

    public LoginAdminResponse login(LoginDto loginDto) {
        Administrator administrator = repository.findByEmail(loginDto.getEmail());
        if (administrator != null && administrator.getPassword() != null && administrator.getPassword().equals(loginDto.getPassword())) {
            return new LoginAdminResponse("Login realizado com sucesso.", administrator.getId(), administrator.getName());
        } else {
            throw new RuntimeException("Dados inválidos, tente novamente.");
        }
    }
}

package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LogisticCompanyCreationResponse;
import com.example.rd_log_api.service.LogisticCompanyService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logistic-companies")
public class LogisticCompanyController {
    @Autowired
    LogisticCompanyService service;

    @GetMapping
    public ResponseEntity<List<LogisticCompanyDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticCompanyDto> getById(@PathVariable Long id) throws NotFoundException {
        LogisticCompanyDto foundedLogisticCompany = service.getById(id);
        return ResponseEntity.ok().body(foundedLogisticCompany);
    }

    @PostMapping
    public ResponseEntity<LogisticCompanyCreationResponse> createLogisticCompany(@RequestBody @Valid LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompanyCreationRequest createdLogisticCompany = service.createLogisticCompany(logisticCompany);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LogisticCompanyCreationResponse(createdLogisticCompany));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogisticCompanyDto> updateLogisticCompany(@RequestBody @Valid LogisticCompanyUpdateRequest logisticCompany, @PathVariable Long id) throws NotFoundException {
        LogisticCompanyDto updatedLogisticCompany = service.updateLogisticCompany(id, logisticCompany);
        return ResponseEntity.ok().body(updatedLogisticCompany);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteLogisticCompany(@PathVariable Long id) throws NotFoundException {
        service.deleteLogisticCompany(id);
        return ResponseEntity.noContent().build();
    }
}

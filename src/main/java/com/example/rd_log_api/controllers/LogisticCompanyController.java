package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.service.LogisticCompanyService;
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
    public ResponseEntity<List<LogisticCompanyCreationRequest>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogisticCompany> getById(@PathVariable Long id) {
        LogisticCompany foundedLogisticCompany = service.getById(id);
        return ResponseEntity.ok().body(foundedLogisticCompany);
    }

    @PostMapping
    public ResponseEntity<LogisticCompanyCreationRequest> createLogisticCompany(@RequestBody @Valid LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompanyCreationRequest createdLogisticCompany = service.createLogisticCompany(logisticCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLogisticCompany);
    }
}

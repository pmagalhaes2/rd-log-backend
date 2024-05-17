package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.entities.CustomLogisticCompany;
import com.example.rd_log_api.service.CustomLogisticCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/custom-logistic-companies")
public class CustomLogisticCompanyController {

    private final CustomLogisticCompanyService customLogisticCompanyService;

    @Autowired
    public CustomLogisticCompanyController(CustomLogisticCompanyService customLogisticCompanyService) {
        this.customLogisticCompanyService = customLogisticCompanyService;
    }

    @GetMapping("/first")
    public ResponseEntity<CustomLogisticCompany> getFirstCustomLogisticCompany() {
        CustomLogisticCompany firstCompany = customLogisticCompanyService.getFirstCompany();
        return ResponseEntity.ok(firstCompany);
    }
}
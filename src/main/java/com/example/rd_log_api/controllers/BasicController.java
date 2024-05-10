package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.gateways.DistanceMatrixService;
import com.example.rd_log_api.service.LogisticCompanyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class BasicController {

    @Autowired
    DistanceMatrixService distanceMatrixService;
    
    @Autowired
    LogisticCompanyService service;

    @GetMapping("/distance")
    public String getDistance(String origins, String destinations, String key) {
        return distanceMatrixService.getDistance(origins, destinations,
                key);
    }

    @PostMapping("/logistic-companies")
    public ResponseEntity<LogisticCompanyCreationRequest> createLogisticCompany(@RequestBody @Valid LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompanyCreationRequest createdlogisticCompany = service.createLogisticCompany(logisticCompany);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdlogisticCompany);
    }
}


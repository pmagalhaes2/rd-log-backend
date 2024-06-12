package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.gateways.DistanceMatrixService;
import com.example.rd_log_api.service.LogisticCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
public class DistanceController {

    @Autowired
    DistanceMatrixService distanceMatrixService;
    @Autowired
    private LogisticCompanyService logisticCompanyService;

    @GetMapping("/distance")
    public String getDistance(String origins, String destinations, String key) {
        return distanceMatrixService.getDistance(origins, destinations, key);
    }

    @GetMapping("/nearest")
    public ResponseEntity<List<LogisticCompanyDto>> findNearestCompanies(@RequestParam String originState, @RequestParam String destinationState) {
        List<LogisticCompanyDto> nearestCompanies = logisticCompanyService.findCompaniesInSameState(originState, destinationState);

        return ResponseEntity.ok(nearestCompanies);
    }
}

package com.example.rd_log_api.controllers;

import com.example.rd_log_api.gateways.DistanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class BasicController {

    @Autowired
    DistanceMatrixService distanceMatrixService;

    @GetMapping("/distance")
    public String getDistance(String origins, String destinations, String key) {
        return distanceMatrixService.getDistance(origins, destinations, key);
    }
}


package com.example.rd_log_api.controllers;

import com.example.rd_log_api.gateways.DistanceMatrixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DistanceController {

    @Autowired
    DistanceMatrixService distanceMatrixService;

    @GetMapping("/distance")
    public ResponseEntity<String> getDistance(@RequestParam("origin1") String origin1, @RequestParam("origin2") String origin2,
                                              @RequestParam("destination1") String destination1, @RequestParam("destination2") String destination2,
                                              @RequestParam("key") String key) {

        String response1 = distanceMatrixService.getDistance(origin1, destination1, key);
        String response2 = distanceMatrixService.getDistance(origin2, destination2, key);


        boolean sameCityState = verifyCityState(response1, response2);

        if (sameCityState) {
            return ResponseEntity.ok("Os endereços estão dentro da mesma cidade/estado");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Os endereços estão em cidades/estados diferentes");
        }
    }

    private boolean verifyCityState(String response1, String response2) {

        return true;
    }
}

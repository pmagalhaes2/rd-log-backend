package com.example.rd_log_api.controllers;

import com.example.rd_log_api.gateways.ZipCodeService;
import com.example.rd_log_api.domain.dto.responses.ZipCodeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping("/verifydelivery")
    public ResponseEntity<String> verifyDelivery(@RequestParam("origins") String originCep,
                                                 @RequestParam("destinations") String destinationCep) {

        ZipCodeResponse originInfo = zipCodeService.getZipCode(originCep);
        ZipCodeResponse destinationInfo = zipCodeService.getZipCode(destinationCep);

        boolean sameCityState = verifyCityState(originInfo, destinationInfo);

        if (sameCityState) {
            return ResponseEntity.ok("Os endereços estão dentro da área de atuação!");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Entrega fora da área de atuação, tente novo endereço!");
        }
    }

    private boolean verifyCityState(ZipCodeResponse originInfo, ZipCodeResponse destinationInfo) {
        if (originInfo != null && destinationInfo != null) {
            return originInfo.getLocalidade().equals(destinationInfo.getLocalidade()) &&
                    originInfo.getUf().equals(destinationInfo.getUf());
        }
        return false;
    }
}

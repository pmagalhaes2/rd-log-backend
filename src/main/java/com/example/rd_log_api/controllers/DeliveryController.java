package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.responses.LogisticCompanyDistanceDto;
import com.example.rd_log_api.domain.dto.responses.ZipCodeResponse;
import com.example.rd_log_api.gateways.DistanceMatrixService;
import com.example.rd_log_api.gateways.ZipCodeService;
import com.example.rd_log_api.service.LogisticCompanyService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class DeliveryController {

    @Autowired
    DistanceMatrixService distanceMatrixService;
    @Autowired
    private ZipCodeService zipCodeService;
    @Autowired
    private LogisticCompanyService logisticCompanyService;

    @GetMapping("/delivery-details")
    public ResponseEntity<List<LogisticCompanyDistanceDto>> verifyDelivery(@RequestParam("origins") String originCep,
                                                                           @RequestParam("destinations") String destinationCep,
                                                                           @RequestParam("key") String apiKey) {

        ZipCodeResponse originInfo = zipCodeService.getZipCode(originCep);
        ZipCodeResponse destinationInfo = zipCodeService.getZipCode(destinationCep);

        boolean sameState = verifyState(originInfo, destinationInfo);

        if (!sameState) {
            throw new EntityNotFoundException("Endereços informados não estão dentro do mesmo estado.");
        }

        String distanceResponse = distanceMatrixService.getDistance(originCep, destinationCep, apiKey);

        String distanceSupplierStore = extractDistanceText(distanceResponse);
        String durationSupplierStore = extractDurationText(distanceResponse);

        List<LogisticCompanyDto> companiesInOriginState = logisticCompanyService.findCompaniesInSameState(originInfo.getUf());

        if (distanceSupplierStore == null || durationSupplierStore == null) {
            throw new NotFoundException(String.class, "Informação de distância ou duração não encontrada.");
        }

        LogisticCompanyDistanceDto closestCompany = null;
        double shortestDistance = Double.MAX_VALUE;

        LogisticCompanyDistanceDto cheapestCompany = null;
        LogisticCompanyDistanceDto secondCheapestCompany = null;
        double lowestPrice = Double.MAX_VALUE;
        double secondLowestPrice = Double.MAX_VALUE;

        if (companiesInOriginState.isEmpty()) {
            throw new EntityNotFoundException("Não foram encontradas transportadoras disponíveis para o estado informado.");
        }

        for (LogisticCompanyDto company : companiesInOriginState) {
            String distanceSupplierLogisticCompany = distanceMatrixService.getDistance(originInfo.getCep(), company.getAddress().getZipCode(), apiKey);
            String distanceText = extractDistanceText(distanceSupplierLogisticCompany);

            if (distanceText != null) {
                double distanceSupplierStoreValue = Double.parseDouble(distanceSupplierStore.replace(" km", ""));
                double distanceTextValue = Double.parseDouble(distanceText.replace(" km", ""));
                String durationText = extractDurationText(distanceSupplierLogisticCompany);

                double durationSupplierStoreValue = convertDurationToSeconds(durationSupplierStore);
                double durationTextValue = convertDurationToSeconds(durationText);
                double totalDuration = durationSupplierStoreValue + durationTextValue;
                String totalDurationFormatted = formatDuration(totalDuration);

                double totalDistance = distanceSupplierStoreValue + distanceTextValue;
                double totalPrice = totalDistance * company.getPrice_km();

                if (totalDistance < shortestDistance) {
                    shortestDistance = totalDistance;
                    closestCompany = new LogisticCompanyDistanceDto(company.getId(), company.getName(),
                            String.valueOf(totalDistance), totalDurationFormatted, totalPrice, true);
                }

                if (totalPrice < lowestPrice) {
                    secondLowestPrice = lowestPrice;
                    secondCheapestCompany = cheapestCompany;
                    lowestPrice = totalPrice;
                    cheapestCompany = new LogisticCompanyDistanceDto(company.getId(), company.getName(),
                            String.valueOf(totalDistance), totalDurationFormatted, totalPrice, false);
                } else if (totalPrice < secondLowestPrice) {
                    secondLowestPrice = totalPrice;
                    secondCheapestCompany = new LogisticCompanyDistanceDto(company.getId(), company.getName(),
                            String.valueOf(totalDistance), totalDurationFormatted, totalPrice, false);
                }
            }
        }

        List<LogisticCompanyDistanceDto> results = new ArrayList<>();
        if (closestCompany != null) {
            results.add(closestCompany);
        }
        if (cheapestCompany != null && !cheapestCompany.equals(closestCompany)) {
            results.add(cheapestCompany);
        } else if (secondCheapestCompany != null) {
            results.add(secondCheapestCompany);
        }

        return ResponseEntity.ok(results);
    }

    private String extractDistanceText(String distanceResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(distanceResponse, JsonObject.class);

        if (jsonObject != null && jsonObject.has("rows") && !jsonObject.get("rows").getAsJsonArray().isEmpty()) {
            JsonObject row = jsonObject.getAsJsonArray("rows").get(0).getAsJsonObject();
            if (row.has("elements") && !row.get("elements").getAsJsonArray().isEmpty()) {
                JsonObject element = row.getAsJsonArray("elements").get(0).getAsJsonObject();
                if (element.has("distance")) {
                    return element.getAsJsonObject("distance").get("text").getAsString();
                }
            }
        }

        return null;
    }

    private String extractDurationText(String durationResponse) {
        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(durationResponse, JsonObject.class);

        if (jsonObject != null && jsonObject.has("rows") && !jsonObject.get("rows").getAsJsonArray().isEmpty()) {
            JsonObject row = jsonObject.getAsJsonArray("rows").get(0).getAsJsonObject();
            if (row.has("elements") && !row.get("elements").getAsJsonArray().isEmpty()) {
                JsonObject element = row.getAsJsonArray("elements").get(0).getAsJsonObject();
                if (element.has("duration")) {
                    return element.getAsJsonObject("duration").get("text").getAsString();
                }
            }
        }

        return null;
    }

    private double convertDurationToSeconds(String durationText) {
        if (durationText == null || durationText.isEmpty()) {
            return 0;
        }

        String[] parts = durationText.split(" ");
        double time = 0;

        for (int i = 0; i < parts.length; i += 2) {
            int value = Integer.parseInt(parts[i]);
            String unit = parts[i + 1];
            if (unit.contains("hour")) {
                time += value * 3600;
            } else if (unit.contains("min")) {
                time += value * 60;
            }
        }

        return time;
    }

    private String formatDuration(double totalSeconds) {
        int hours = (int) (totalSeconds / 3600);
        int minutes = (int) ((totalSeconds % 3600) / 60);
        return String.format("%d horas %d minutes", hours, minutes);
    }


    private boolean verifyState(ZipCodeResponse originInfo, ZipCodeResponse destinationInfo) {
        if (originInfo != null && destinationInfo != null) {
            return originInfo.getUf().equals(destinationInfo.getUf());
        }
        return false;
    }
}
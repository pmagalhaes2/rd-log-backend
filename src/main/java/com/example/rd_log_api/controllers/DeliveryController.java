package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LogisticCompanyDistanceDto;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.responses.ZipCodeResponse;
import com.example.rd_log_api.gateways.DistanceMatrixService;
import com.example.rd_log_api.gateways.ZipCodeService;
import com.example.rd_log_api.service.LogisticCompanyService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Verify delivery details", description = "Returns the closest and cheapest logistic companies based on the given origin and destination zip codes.", tags = {"Delivery"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Delivery details retrieved successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = LogisticCompanyDistanceDto.class)))),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid Request", content = @Content)
    })
    @GetMapping("/delivery-details")
    public ResponseEntity<List<LogisticCompanyDistanceDto>> verifyDelivery(
            @RequestParam("origins") String originCep,
            @RequestParam("destinations") String destinationCep,
            @RequestParam("key") String apiKey) {

        ZipCodeResponse originInfo = zipCodeService.getZipCode(originCep);
        ZipCodeResponse destinationInfo = zipCodeService.getZipCode(destinationCep);

        if (originInfo.getUf() == null || destinationInfo.getUf() == null) {
            throw new EntityNotFoundException("Erro ao consultar CEP.");
        }

        boolean sameState = verifyState(originInfo, destinationInfo);

        if (!sameState) {
            throw new EntityNotFoundException("Endereços informados não estão dentro do mesmo estado.");
        }

        String distanceResponse = distanceMatrixService.getDistance(originCep, destinationCep, apiKey);

        String distanceSupplierStore = extractDistanceText(distanceResponse);
        String durationSupplierStore = extractDurationText(distanceResponse);

        List<LogisticCompanyDto> companiesInOriginState = logisticCompanyService.findCompaniesInSameState(
                originInfo.getUf());

        if (distanceSupplierStore == null || durationSupplierStore == null) {
            throw new NotFoundException(String.class, "Informação de distância ou duração não encontrada.");
        }

        LogisticCompanyDistanceDto closestCompany = null;
        double shortestDistance = Double.MAX_VALUE;

        LogisticCompanyDistanceDto cheapestCompany = null;
        double lowestPrice = Double.MAX_VALUE;

        if (companiesInOriginState.isEmpty()) {
            throw new EntityNotFoundException(
                    "Não foram encontradas transportadoras disponíveis para o estado informado.");
        }

        for (LogisticCompanyDto company : companiesInOriginState) {
            String distanceSupplierLogisticCompanyToOrigin = distanceMatrixService.getDistance(
                    company.getAddress().getZipCode(), originCep, apiKey);
            String distanceSupplierOriginToDestination = distanceMatrixService.getDistance(originCep, destinationCep,
                    apiKey);

            String distanceTextOrigin = extractDistanceText(distanceSupplierLogisticCompanyToOrigin);
            String distanceTextDestination = extractDistanceText(distanceSupplierOriginToDestination);

            if (distanceTextOrigin != null && distanceTextDestination != null) {
                double distanceSupplierLogisticCompanyValue = Double.parseDouble(distanceTextOrigin.replace(" km", ""));
                double distanceSupplierOriginToDestinationValue = Double.parseDouble(
                        distanceTextDestination.replace(" km", ""));
                double totalDistance = distanceSupplierLogisticCompanyValue + distanceSupplierOriginToDestinationValue;

                String durationSupplierLogisticCompanyToOrigin = extractDurationText(
                        distanceSupplierLogisticCompanyToOrigin);
                String durationSupplierOriginToDestination = extractDurationText(distanceSupplierOriginToDestination);
                String totalDurationFormatted = "";

                if (durationSupplierLogisticCompanyToOrigin != null && durationSupplierOriginToDestination != null) {
                    double durationSupplierLogisticCompanyValue = convertDurationToSeconds(
                            durationSupplierLogisticCompanyToOrigin);
                    double durationSupplierOriginToDestinationValue = convertDurationToSeconds(
                            durationSupplierOriginToDestination);
                    double totalDuration = durationSupplierLogisticCompanyValue + durationSupplierOriginToDestinationValue;
                    totalDurationFormatted = formatDuration(totalDuration);
                }

                double totalPrice = totalDistance * company.getPrice_km();

                if (totalDistance < shortestDistance) {
                    shortestDistance = totalDistance;
                    closestCompany = new LogisticCompanyDistanceDto(company.getId(), company.getName(),
                            String.valueOf(totalDistance), totalDurationFormatted, totalPrice, true);
                }

                if (totalPrice < lowestPrice) {
                    lowestPrice = totalPrice;
                    cheapestCompany = new LogisticCompanyDistanceDto(company.getId(), company.getName(),
                            String.valueOf(totalDistance), totalDurationFormatted, totalPrice, false);
                }
            }
        }

        List<LogisticCompanyDistanceDto> results = new ArrayList<>();
        if (cheapestCompany != null) {
            results.add(cheapestCompany);
        }
        if (closestCompany != null && !closestCompany.equals(cheapestCompany)) {
            results.add(closestCompany);
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
                    String durationText = element.getAsJsonObject("duration").get("text").getAsString();
                    double durationInSeconds = convertDurationToSeconds(durationText);
                    return formatDuration(durationInSeconds);
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

    private String formatDuration(double durationInSeconds) {
        int totalMinutes = (int) (durationInSeconds / 60);
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;

        if (hours > 0) {
            return hours + " hora" + (hours > 1 ? "s" : "") + " " + minutes + " minuto" + (minutes != 1 ? "s" : "");
        } else {
            return minutes + " minuto" + (minutes != 1 ? "s" : "");
        }
    }


    private boolean verifyState(ZipCodeResponse originInfo, ZipCodeResponse destinationInfo) {
        if (originInfo.getUf() != null && destinationInfo.getUf() != null) {
            return originInfo.getUf().equals(destinationInfo.getUf());
        }
        return false;
    }
}
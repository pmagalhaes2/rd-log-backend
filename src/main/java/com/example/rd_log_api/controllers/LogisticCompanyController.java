package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LoginResponse;
import com.example.rd_log_api.domain.dto.responses.LogisticCompanyCreationResponse;
import com.example.rd_log_api.service.LogisticCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    private final LogisticCompanyService service;

    @Autowired
    public LogisticCompanyController(LogisticCompanyService service) {
        this.service = service;
    }

    @Operation(summary = "Get all logistic companies", description = "Retrieves a list of all logistic companies.", tags = {"Logistic Companies"})
    @GetMapping
    public ResponseEntity<List<LogisticCompanyDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Get logistic company by ID", description = "Retrieves a logistic company by its ID.", tags = {"Logistic Companies"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Logistic company found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogisticCompanyDto.class))), @ApiResponse(responseCode = "404", description = "Logistic company not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<LogisticCompanyDto> getById(@PathVariable Long id) throws NotFoundException {
        LogisticCompanyDto foundedLogisticCompany = service.getById(id);
        return ResponseEntity.ok().body(foundedLogisticCompany);
    }

    @Operation(summary = "Create a new logistic company", description = "Creates a new logistic company.", tags = {"Logistic Companies"})
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Logistic company created successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogisticCompanyCreationResponse.class))), @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
    @PostMapping
    public ResponseEntity<LogisticCompanyCreationResponse> createLogisticCompany(@RequestBody @Valid LogisticCompanyCreationRequest logisticCompany) {
        LogisticCompanyCreationRequest createdLogisticCompany = service.createLogisticCompany(logisticCompany);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new LogisticCompanyCreationResponse(createdLogisticCompany));
    }

    @Operation(summary = "Update an existing logistic company", description = "Updates an existing logistic company.", tags = {"Logistic Companies"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Logistic company updated successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LogisticCompanyDto.class))), @ApiResponse(responseCode = "404", description = "Logistic company not found", content = {})})
    @PutMapping("/{id}")
    public ResponseEntity<LogisticCompanyDto> updateLogisticCompany(@RequestBody @Valid LogisticCompanyUpdateRequest logisticCompany, @PathVariable Long id) throws NotFoundException {
        LogisticCompanyDto updatedLogisticCompany = service.updateLogisticCompany(id, logisticCompany);
        return ResponseEntity.ok().body(updatedLogisticCompany);
    }

    @Operation(summary = "Delete a logistic company", description = "Deletes a logistic company by its ID.", tags = {"Logistic Companies"})
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "Logistic company deleted successfully"), @ApiResponse(responseCode = "404", description = "Logistic company not found"), @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteLogisticCompany(@PathVariable Long id) throws NotFoundException {
        service.deleteLogisticCompany(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Login", description = "Logs in a logistic company.", tags = {"Authentication"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Logged in successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = LoginResponse.class))), @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDto loginDTO) {
        LoginResponse loginResponse = service.login(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }
}

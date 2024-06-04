package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.AdministratorDto;
import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.AdministratorCreationRequest;
import com.example.rd_log_api.domain.dto.requests.AdministratorUpdateRequest;
import com.example.rd_log_api.domain.dto.responses.LoginAdminResponse;
import com.example.rd_log_api.service.AdministratorService;
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
@RequestMapping("/administrators")
public class AdministratorController {
    private final AdministratorService service;

    @Autowired
    public AdministratorController(AdministratorService service) {
        this.service = service;
    }

    @Operation(summary = "Get all administrators", description = "Retrieves a list of all administrators.", tags = {
            "Administrators"})
    @GetMapping
    public ResponseEntity<List<AdministratorDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @Operation(summary = "Create a new administrator", description = "Creates a new administrator.", tags = {
            "Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Administrator created", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<AdministratorDto> createAdministrator(@RequestBody @Valid AdministratorCreationRequest administrator) {
        AdministratorDto createdAdministrator = service.createAdministrator(administrator);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdAdministrator);
    }

    @Operation(summary = "Get administrator by ID", description = "Retrieves an administrator by their ID.", tags = {
            "Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator found", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> getById(@PathVariable Long id) throws NotFoundException {
        AdministratorDto foundedAdministrator = service.getById(id);
        return ResponseEntity.ok().body(foundedAdministrator);
    }

    @Operation(summary = "Update an existing administrator", description = "Updates an existing administrator.", tags = {
            "Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Administrator updated", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content)})
    @PutMapping("/{id}")
    public ResponseEntity<AdministratorDto> updateAdministrator(@RequestBody @Valid AdministratorUpdateRequest administrator, @PathVariable Long id) throws NotFoundException {
        AdministratorDto updatedAdministrator = service.updateAdministrator(id, administrator);
        return ResponseEntity.ok().body(updatedAdministrator);
    }

    @Operation(summary = "Delete an administrator", description = "Deletes an administrator by their ID.", tags = {
            "Administrators"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Administrator updated", content = @Content(mediaType =
                    "application/json", schema = @Schema(implementation = AdministratorDto.class))),
            @ApiResponse(responseCode = "404", description = "Administrator not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteLAdministrator(@PathVariable Long id) throws NotFoundException {
        service.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Login", description = "Logs in an administrator.", tags = {"Authentication"})
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Logged in successfully", content =
    @Content(mediaType = "application/json", schema = @Schema(implementation = LoginAdminResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    @PostMapping("/login")
    public ResponseEntity<LoginAdminResponse> login(@RequestBody LoginDto loginDTO) {
        LoginAdminResponse loginAdminResponse = service.login(loginDTO);
        return ResponseEntity.ok(loginAdminResponse);
    }
}

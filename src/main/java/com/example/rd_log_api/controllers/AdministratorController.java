package com.example.rd_log_api.controllers;

import com.example.rd_log_api.domain.dto.LoginDto;
import com.example.rd_log_api.domain.dto.exception.NotFoundException;
import com.example.rd_log_api.domain.dto.requests.*;
import com.example.rd_log_api.service.AdministratorService;
import com.example.rd_log_api.domain.dto.responses.LoginAdminResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/administrators")
public class AdministratorController {private final AdministratorService service;

    @Autowired
    public AdministratorController(AdministratorService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AdministratorDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministratorDto> getById(@PathVariable Long id) throws NotFoundException {
        AdministratorDto foundedAdministrator = service.getById(id);
        return ResponseEntity.ok().body(foundedAdministrator);
    }

    @PostMapping
    public ResponseEntity<AdministratorDto> createAdministrator(@RequestBody @Valid AdministratorCreationRequest administrator) {
        AdministratorDto createdAdministrator = service.createAdministrator(administrator);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdAdministrator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministratorDto> updateAdministrator(@RequestBody @Valid AdministratorUpdateRequest administrator, @PathVariable Long id) throws NotFoundException {
        AdministratorDto updatedAdministrator = service.updateAdministrator(id, administrator);
        return ResponseEntity.ok().body(updatedAdministrator);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteLAdministrator(@PathVariable Long id) throws NotFoundException {
        service.deleteAdministrator(id);
        return ResponseEntity.noContent().build();
    }


  @PostMapping("/login")
    public ResponseEntity<LoginAdminResponse> login(@RequestBody LoginDto loginDTO) {
        LoginAdminResponse loginAdminResponse = service.login(loginDTO);
        return ResponseEntity.ok(loginAdminResponse);
    }
}

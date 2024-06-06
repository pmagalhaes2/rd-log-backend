package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AdministratorDto;
import com.example.rd_log_api.domain.dto.requests.*;
import com.example.rd_log_api.domain.entities.Administrator;

public class AdministratorMapper {
    public static Administrator toEntityFromCreationRequest(AdministratorCreationRequest request) {
    return new Administrator(
            request.getId(),
            request.getName(),
            request.getCpf(),
            request.getEmail(),
            request.getPassword()
    );
}

    public static Administrator toEntityFromAdministratorDto(AdministratorDto dto) {
        return new Administrator(
                dto.getId(),
                dto.getName(),
                dto.getCpf(),
                dto.getEmail()
        );
    }

    public static AdministratorCreationRequest toCreationRequestDto(Administrator entity) {
        return new AdministratorCreationRequest(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail(),
                entity.getPassword()
        );
    }

    public static AdministratorDto toAdministratorDto(Administrator entity) {
        return new AdministratorDto(
                entity.getId(),
                entity.getName(),
                entity.getCpf(),
                entity.getEmail()

        );
    }
}
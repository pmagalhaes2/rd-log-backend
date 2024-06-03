package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyDto;
import com.example.rd_log_api.domain.entities.LogisticCompany;

public class LogisticCompanyMapper {
    public static LogisticCompany toEntityFromCreationRequest(LogisticCompanyCreationRequest request) {
        return new LogisticCompany(
                request.getId(),
                request.getName(),
                request.getCnpj(),
                request.getOpening_hours(),
                request.getClosing_hours(),
                request.getPhone_number(),
                request.getEmail(),
                request.getPassword(),
                request.address
        );
    }

    public static LogisticCompany toEntityFromLogisticDto(LogisticCompanyDto dto) {
        return new LogisticCompany(
                dto.getId(),
                dto.getName(),
                dto.getCnpj(),
                dto.getOpening_hours(),
                dto.getClosing_hours(),
                dto.getPhone_number(),
                dto.getEmail(),
                dto.address
        );
    }

    public static LogisticCompanyCreationRequest toCreationRequestDto(LogisticCompany entity) {
        return new LogisticCompanyCreationRequest(
                entity.getAddress(),
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getOpeningHours(),
                entity.getClosingHours(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getPassword()
                );
    }

    public static LogisticCompanyDto toLogisticDto(LogisticCompany entity) {
        return new LogisticCompanyDto(
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getOpeningHours(),
                entity.getClosingHours(),
                entity.getPhoneNumber(),
                entity.getEmail()
        );
    }
}

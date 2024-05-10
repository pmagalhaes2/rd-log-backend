package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.entities.LogisticCompany;


public class LogisticCompanyMapper {
    public static LogisticCompany toEntity(LogisticCompanyCreationRequest request) {
        return new LogisticCompany(
                request.getId(),
                request.getName(),
                request.getCnpj(),
                request.getOpening_hours(),
                request.getClosing_hours(),
                request.getPhone_number(),
                request.getEmail(),
                request.getAccepts_dangerous_loads()
        );
    }

    public static LogisticCompanyCreationRequest toDto(LogisticCompany entity) {
        return new LogisticCompanyCreationRequest(
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getOpening_hours(),
                entity.getClosing_hours(),
                entity.getPhone_number(),
                entity.getEmail(),
                entity.getAccepts_dangerous_loads()
        );
    }
}

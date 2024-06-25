package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.LogisticCompany;

public class LogisticCompanyMapper {

    public static LogisticCompany toEntityFromCreationRequest(Long request) {
        AddressDto addressDto = request.getAddress();
        Address address = new Address(
                addressDto.getId(),
                addressDto.getComplement(),
                addressDto.getValue(),
                addressDto.getNumber(),
                addressDto.getCity(),
                addressDto.getState(),
                addressDto.getZipCode()
        );

        return new LogisticCompany(
                request.getId(),
                request.getName(),
                request.getCnpj(),
                request.getPrice_km(),
                request.getOpening_hours(),
                request.getClosing_hours(),
                request.getPhone_number(),
                request.getEmail(),
                request.getPassword(),
                address
        );
    }

    public static LogisticCompanyCreationRequest toCreationRequestDto(LogisticCompany entity) {
        Address address = entity.getAddress();
        AddressDto addressDto = new AddressDto(
                address.getId(),
                address.getValue(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getComplement()
        );

        return new LogisticCompanyCreationRequest(
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getOpeningHours(),
                entity.getClosingHours(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                entity.getPassword(),
                addressDto,
                entity.getPriceKm()
                );
    }

    public static LogisticCompanyDto toLogisticDto(LogisticCompany entity) {
        Address address = entity.getAddress();
        AddressDto addressDto = new AddressDto(
                address.getId(),
                address.getValue(),
                address.getNumber(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getComplement()
        );

        return new LogisticCompanyDto(
                entity.getId(),
                entity.getName(),
                entity.getCnpj(),
                entity.getPriceKm(),
                entity.getOpeningHours(),
                entity.getClosingHours(),
                entity.getPhoneNumber(),
                entity.getEmail(),
                addressDto
        );
    }
}
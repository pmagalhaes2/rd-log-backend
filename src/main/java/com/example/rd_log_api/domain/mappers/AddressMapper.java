package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.example.rd_log_api.domain.dto.requests.AddressCreationRequest;
import com.example.rd_log_api.domain.entities.Address;


public class AddressMapper {
    public static Address toEntityFromCreationRequest(AddressCreationRequest request) {
        return new Address(
                request.getId(),
                request.getType(),
                request.getValue(),
                request.getNumber(),
                request.getCity(),
                request.getState(),
                request.getZipCode()
        );
    }

    public static Address toEntityFromAddressDto(AddressDto dto) {
        return new Address(
                dto.getId(),
                dto.getType(),
                dto.getValue(),
                dto.getNumber(),
                dto.getCity(),
                dto.getState(),
                dto.getZipCode()
        );
    }

    public static AddressCreationRequest toCreationRequestDto(Address entity) {
        return new AddressCreationRequest(
                entity.getId(),
                entity.getType(),
                entity.getValue(),
                entity.getNumber(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode()
        );
    }

    public static AddressDto toAddressDto(Address entity) {
        return new AddressDto(
                entity.getId(),
                entity.getType(),
                entity.getValue(),
                entity.getNumber(),
                entity.getCity(),
                entity.getState(),
                entity.getZipCode()

        );
    }
}


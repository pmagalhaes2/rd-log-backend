package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.Orders;

import java.util.List;

public class OrdersMapper {
    public static OrdersDto toOrdersDto(Orders entity) {
        Address originAddress = entity.getOriginAddress();
        Address destinationAddress = entity.getDestinationAddress();
        AddressDto originAddressDto = new AddressDto(
                originAddress.getId(),
                originAddress.getValue(),
                originAddress.getNumber(),
                originAddress.getCity(),
                originAddress.getState(),
                originAddress.getZipCode(),
                originAddress.getComplement()

        );

        AddressDto destinationAddressDto = new AddressDto(
                destinationAddress.getId(),
                destinationAddress.getValue(),
                destinationAddress.getNumber(),
                destinationAddress.getCity(),
                destinationAddress.getState(),
                destinationAddress.getZipCode(),
                destinationAddress.getComplement()
        );

        return new OrdersDto(
                entity.getId(),
                entity.getCreated_at(),
                entity.getUpdated_at(),
                entity.getSupplier_id(),
                entity.getStatus(),
                originAddressDto,
                destinationAddressDto
        );

    }

    public static Orders toEntityFromDto (OrdersDto dto) {
        AddressDto originAddressDto = getOriginAddress();
        AddressDto destinationAddressDto = getDestinationAddress();
        Address originAddress = new Address(
                originAddressDto.getId(),
                originAddressDto.getValue(),
                originAddressDto.getNumber(),
                originAddressDto.getCity(),
                originAddressDto.getState(),
                originAddressDto.getZipCode(),
                originAddressDto.getComplement()
        );

        Address destinationAddress = new Address(
                destinationAddressDto.getId(),
                destinationAddressDto.getValue(),
                destinationAddressDto.getNumber(),
                destinationAddressDto.getCity(),
                destinationAddressDto.getState(),
                destinationAddressDto.getZipCode(),
                destinationAddressDto.getComplement()
        );

        return new Orders(
                dto.getId(),
                dto.getCreated_at(),
                dto.getUpdated_at(),
                dto.getSupplier_id(),
                dto.getStatus(),
                originAddress,
                destinationAddress
        );
    }
}

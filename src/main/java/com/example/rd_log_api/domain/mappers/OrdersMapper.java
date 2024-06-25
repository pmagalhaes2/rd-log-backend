package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.example.rd_log_api.domain.dto.LogisticCompanyDto;
import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.entities.Orders;

import java.util.List;

public class OrdersMapper {
    public static OrdersDto toOrdersDto(Orders entity) {
        Address originAddress = entity.getOrigin_address();
        Address destinationAddress = entity.getDestination_address();
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
                destinationAddressDto,
                entity.getLogistic_company_id().getId()
        );

    }

    public static Orders toEntityFromDto (OrdersDto dto) {
        AddressDto originAddressDto = dto.getOrigin_address();
        AddressDto destinationAddressDto = dto.getDestination_address();
        Long logisticCompanyDto = dto.getLogistic_company_id();

        return new Orders(
                dto.getId(),
                dto.getCreated_at(),
                dto.getUpdated_at(),
                dto.getSupplier_id(),
                dto.getStatus(),
                LogisticCompanyMapper.toEntityFromCreationRequest(logisticCompanyDto),
                AddressMapper.toEntityFromAddressDto(originAddressDto),
                AddressMapper.toEntityFromAddressDto(destinationAddressDto)
        );
    }
}

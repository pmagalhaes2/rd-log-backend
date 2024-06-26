package com.example.rd_log_api.domain.mappers;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.example.rd_log_api.domain.dto.OrdersDto;
import com.example.rd_log_api.domain.entities.Address;
import com.example.rd_log_api.domain.entities.LogisticCompany;
import com.example.rd_log_api.domain.entities.Orders;

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

        Long logisticCompanyId = entity.getLogistic_company() != null ? entity.getLogistic_company().getId() : null;

        return new OrdersDto(
                entity.getId(),
                entity.getCreated_at(),
                entity.getUpdated_at(),
                entity.getSupplier_id(),
                entity.getStatus(),
                originAddressDto,
                destinationAddressDto,
                logisticCompanyId
        );

    }

    public static Orders toEntityFromDto(OrdersDto dto) {
        AddressDto originAddressDto = dto.getOrigin_address();
        AddressDto destinationAddressDto = dto.getDestination_address();

        LogisticCompany logisticCompany = null;
        if (dto.getLogistic_company_id() != null) {
            logisticCompany = new LogisticCompany();
            logisticCompany.setId(dto.getLogistic_company_id());
        }

        return new Orders(
                dto.getId(),
                dto.getCreated_at(),
                dto.getUpdated_at(),
                dto.getSupplier_id(),
                dto.getStatus(),
                logisticCompany,
                AddressMapper.toEntityFromAddressDto(originAddressDto),
                AddressMapper.toEntityFromAddressDto(destinationAddressDto)
        );
    }
}

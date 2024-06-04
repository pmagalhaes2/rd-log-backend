package com.example.rd_log_api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private Long id;

    @Schema(example = "Travessa", description = "Type of the address")
    private String type;

    @Schema(example = "Travessa da Conquista", description = "Value of the address")
    private String value;

    @Schema(example = "315", description = "Number of the address")
    private Integer number;

    @Schema(example = "São Bernardo do Campo", description = "City of the address")
    private String city;

    @Schema(example = "SP", description = "State of the address")
    private String state;

    @Schema(example = "09700000", description = "Zip code of the address")
    private String zipCode;
}

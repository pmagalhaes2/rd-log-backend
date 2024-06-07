package com.example.rd_log_api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Schema(example = "Travessa da Conquista", description = "Value of the address")
    private String value;

    @Schema(example = "315", description = "Number of the address")
    private Integer number;

    @Schema(example = "São Bernardo do Campo", description = "City of the address")
    private String city;

    @Schema(example = "SP", description = "State of the address")
    @Size(min = 2, max = 2, message = "size must be 2")
    @Pattern(regexp = "(?i)(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO)",
            message = "must match to an acronym of the Brazilian states")
    private String state;

    @Schema(example = "09700000", description = "Zip code of the address")
    private String zipCode;

    @Schema(example = "Apartamento 01", description = "Complement of the address")
    private String complement;
}

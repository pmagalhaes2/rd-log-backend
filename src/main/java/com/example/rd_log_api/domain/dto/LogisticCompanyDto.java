package com.example.rd_log_api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogisticCompanyDto {
    private Long id;

    @Schema(example = "John Doe", description = "Name of the logistic company")
    private String name;

    @Schema(example = "12345678901234", description = "CNPJ of the logistic company")
    private String cnpj;

    @Schema(example = "2,99", description = "Price of kilometer road of the logistic company")
    private Double price_km;

    @Schema(example = "08:00:00", description = "Opening hours of the logistic company")
    private Time opening_hours;

    @Schema(example = "18:00:00", description = "Closing hours of the logistic company")
    private Time closing_hours;

    @Schema(example = "11912345678", description = "Phone number of the logistic company")
    private String phone_number;

    @Schema(example = "email@example.com", description = "Email of the logistic company")
    private String email;

    private AddressDto address;
}

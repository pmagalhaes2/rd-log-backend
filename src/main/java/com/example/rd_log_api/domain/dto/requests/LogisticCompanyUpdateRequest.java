package com.example.rd_log_api.domain.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class LogisticCompanyUpdateRequest {

    private Long id;

    @NotBlank
    @NotEmpty
    @Schema(example = "John Doe", description = "Updated name of the logistic company")
    private String name;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(example = "08:00:00", description = "Updated opening hours of the logistic company")
    private Time opening_hours;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(example = "18:00:00", description = "Updated closing hours of the logistic company")
    private Time closing_hours;

    @NotBlank
    @NotEmpty
    @Schema(example = "11912345678", description = "Updated phone number of the logistic company")
    private String phone_number;

    @NotBlank
    @NotEmpty
    @Email
    @Schema(example = "updated_email@example.com", description = "Updated email of the logistic company")
    private String email;

    @Schema(description = "The password for the logistic company.", example = "P@ssw0rd")
    private String password;

    private AddressUpdateRequest address;

    @Schema(description = "Price of kilometer road of the logistic company")
    private Double price_km;

}


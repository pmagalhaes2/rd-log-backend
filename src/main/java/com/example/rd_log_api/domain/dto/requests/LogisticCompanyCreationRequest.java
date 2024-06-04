package com.example.rd_log_api.domain.dto.requests;

import com.example.rd_log_api.domain.dto.AddressDto;
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
public class LogisticCompanyCreationRequest {
    private Long id;

    @NotBlank
    @NotEmpty
    @Schema(example = "John Doe", description = "Name of the logistic company")
    private String name;

    @NotBlank
    @NotEmpty
    @Schema(example = "12345678901234", description = "CNPJ of the logistic company")
    private String cnpj;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(example = "08:00:00", description = "Opening hours of the logistic company")
    private Time opening_hours;

    @JsonFormat(pattern = "HH:mm:ss")
    @Schema(example = "18:00:00", description = "Closing hours of the logistic company")
    private Time closing_hours;

    @NotBlank
    @NotEmpty
    @Schema(example = "11912345678", description = "Phone number of the logistic company")
    private String phone_number;

    @NotBlank
    @NotEmpty
    @Email
    @Schema(example = "email@example.com", description = "Email of the logistic company")
    private String email;

    @NotBlank
    @NotEmpty
    @Schema(example = "P@ssW0rd", description = "Password for the logistic company")
    private String password;

    @Schema(description = "Address of the logistic company")
    private AddressDto address;
}

package com.example.rd_log_api.domain.dto.responses;

import com.example.rd_log_api.domain.dto.requests.LogisticCompanyCreationRequest;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class LogisticCompanyCreationResponse {
    private Long id;

    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String cnpj;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time opening_hours;

    @JsonFormat(pattern = "HH:mm:ss")
    private Time closing_hours;

    @NotBlank
    @NotEmpty
    private String phone_number;

    @NotBlank
    @NotEmpty
    @Email
    private String email;

    private Boolean accepts_dangerous_loads = Boolean.FALSE;

    public LogisticCompanyCreationResponse(LogisticCompanyCreationRequest logisticCompany) {
        this.id = logisticCompany.getId();
        this.name = logisticCompany.getName();
        this.cnpj = logisticCompany.getCnpj();
        this.opening_hours = logisticCompany.getOpening_hours();
        this.closing_hours = logisticCompany.getClosing_hours();
        this.phone_number = logisticCompany.getPhone_number();
        this.email = logisticCompany.getEmail();
        this.accepts_dangerous_loads = logisticCompany.getAccepts_dangerous_loads();
    }
}

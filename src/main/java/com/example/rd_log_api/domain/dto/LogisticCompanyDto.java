package com.example.rd_log_api.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class LogisticCompanyDto {
    public String address;
    private Long id;

    private String name;

    private String cnpj;

    private Time opening_hours;

    private Time closing_hours;

    private String phone_number;

    private String email;


    public LogisticCompanyDto(Long id, String name, String cnpj, Time openingHours, Time closingHours, String phoneNumber, String email) {}
}

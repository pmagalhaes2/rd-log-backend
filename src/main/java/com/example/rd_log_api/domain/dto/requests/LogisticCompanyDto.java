package com.example.rd_log_api.domain.dto.requests;

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
    private String name;
    private String cnpj;
    private Time opening_hours;
    private Time closing_hours;

    private String phone_number;
    private String email;

    private AddressDto address;
}

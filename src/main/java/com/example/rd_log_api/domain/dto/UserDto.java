package com.example.rd_log_api.domain.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private Time opening_hours;
    private Time closing_hours;
    private String cnpj;
    private String phone_number;
    private String email;
    private String password;
    private Boolean accepts_dangerous_loads;

    public UserDto(String name, Time openingHours, Time closingHours, String phoneNumber, String email, Boolean acceptsDangerousLoads) {}

}
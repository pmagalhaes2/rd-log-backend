package com.example.rd_log_api.domain.dto.requests;

import lombok.Data;

@Data
public class CustomLogisticCompanyRequest {
    private String name;
    private String cnpj;
    private String openingHours;
    private String closingHours;
    private String phoneNumber;
    private String email;
    private Boolean acceptsDangerousLoads;
    private String password;
}


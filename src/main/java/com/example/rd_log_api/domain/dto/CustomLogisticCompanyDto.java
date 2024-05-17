package com.example.rd_log_api.domain.dto;

import java.sql.Time;

public class CustomLogisticCompanyDto {
    private Long id;
    private String name;
    private String cnpj;
    private Time opening_hours;
    private Time closing_hours;
    private String phone_number;
    private String email;
    private Boolean accepts_dangerous_loads;


}

package com.example.rd_log_api.domain.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticCompanyDistanceDto {
    private Long logistic_id;
    private String logistic_name;
    private String distance;
    private Double price_km;
}

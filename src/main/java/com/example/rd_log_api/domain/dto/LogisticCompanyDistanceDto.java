package com.example.rd_log_api.domain.dto;

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
    private String duration;
    private Double price_km;
    private boolean closest_company;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogisticCompanyDistanceDto that = (LogisticCompanyDistanceDto) o;
        return logistic_id.equals(that.logistic_id);
    }

}



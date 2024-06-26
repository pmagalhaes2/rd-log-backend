package com.example.rd_log_api.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class OrdersDto {
    private Long id;

    private Timestamp created_at;

    private Timestamp updated_at;

    private Long supplier_id;

    private String status;

    private AddressDto origin_address;

    private AddressDto destination_address;

    private Long logistic_company_id;

}

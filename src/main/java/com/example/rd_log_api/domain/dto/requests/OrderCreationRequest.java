package com.example.rd_log_api.domain.dto.requests;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class OrderCreationRequest {
    private Long id;

    private Timestamp created_at;

    private Timestamp updated_at;

    private Long supplier_id;

    private String status;

    private Long logistic_company_id;

    @NotNull
    private AddressDto origin_address;

    @NotNull
    private AddressDto destination_address;

}

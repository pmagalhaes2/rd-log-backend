package com.example.rd_log_api.domain.dto.responses;

import com.example.rd_log_api.domain.dto.AddressDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;

    private Timestamp created_at;

    private Long supplier_id;

    private AddressDto origin_address;

    private AddressDto destination_address;
}

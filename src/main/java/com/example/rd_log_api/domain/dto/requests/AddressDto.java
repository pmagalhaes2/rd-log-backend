package com.example.rd_log_api.domain.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String type;
    private String value;
    private Integer number;
    private String city;
    private String state;
    private String zipCode;
}

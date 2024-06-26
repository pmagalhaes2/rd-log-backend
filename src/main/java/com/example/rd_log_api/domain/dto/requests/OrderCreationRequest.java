package com.example.rd_log_api.domain.dto.requests;

import com.example.rd_log_api.domain.dto.AddressDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

//    @NotBlank
//    @NotEmpty
//    private Timestamp created_at;
//
//    @NotBlank
//    @NotEmpty
//    private Timestamp updated_at;

    @NotBlank
    @NotEmpty
    private Long supplier_id;

//    @NotBlank
//    @NotEmpty
//    private String status;

    @NotBlank
    @NotEmpty
    private AddressDto origin_address;

    @NotBlank
    @NotEmpty
    private AddressDto destination_address;

}

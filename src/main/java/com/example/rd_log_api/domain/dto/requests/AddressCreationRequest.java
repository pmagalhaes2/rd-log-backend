package com.example.rd_log_api.domain.dto.requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AddressCreationRequest {
    private Long id;

    @NotBlank
    @NotEmpty
    private String value;

    @NotBlank
    @NotEmpty
    private Integer number;

    @NotBlank
    @NotEmpty
    private String city;

    @NotBlank
    @NotEmpty
    private String state;

    @NotBlank
    @NotEmpty
    private String zipCode;

    @NotBlank
    private String complement;

}

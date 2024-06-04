package com.example.rd_log_api.domain.dto.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    @Schema(example = "Login realizado com sucesso.")
    private String message;

    @Schema(example = "0")
    private Long logisticCompanyId;

    @Schema(example = "John Doe")
    private String logisticCompanyName;
}
package com.example.rd_log_api.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @Schema(example = "email@example.com", description = "Email of the logistic company")
    private String email;

    @Schema(example = "P@ssW0rd", description = "Password for the logistic company")
    private String password;
}

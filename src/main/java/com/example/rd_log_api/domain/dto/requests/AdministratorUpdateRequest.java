package com.example.rd_log_api.domain.dto.requests;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AdministratorUpdateRequest {
    @NotBlank
    @NotEmpty
    private String name;

    @NotBlank
    @NotEmpty
    private String cpf;

    @NotBlank
    @NotEmpty
    @Email
    private String email;

    private String password;
}
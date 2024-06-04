package com.example.rd_log_api.domain.dto.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
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
public class AdministratorCreationRequest {
    private Long id;

    @NotBlank
    @NotEmpty
    @Schema(description = "The name of the administrator.", example = "John Doe")
    private String name;

    @NotBlank
    @NotEmpty
    @Schema(description = "The CPF of the administrator.", example = "12345678900")
    private String cpf;

    @NotBlank
    @NotEmpty
    @Email
    @Schema(description = "The email address of the administrator.", example = "john@example.com")
    private String email;

    @NotBlank
    @NotEmpty
    @Schema(description = "The password for the administrator.", example = "P@ssw0rd")
    private String password;
}

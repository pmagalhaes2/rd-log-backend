package com.example.rd_log_api.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class AdministratorDto {
    private Long id;

    @Schema(description = "The name of the administrator.", example = "John Doe")
    private String name;

    @Schema(description = "The CPF of the administrator.", example = "12345678900")
    private String cpf;

    @Schema(description = "The email address of the administrator.", example = "john@example.com")
    private String email;


    public AdministratorDto(String name, String cpf, String email) {}
}
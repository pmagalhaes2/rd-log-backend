package com.example.rd_log_api.domain.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String name;

    private String cpf;

    private String email;


    public AdministratorDto(String name, String cpf, String email) {}
}
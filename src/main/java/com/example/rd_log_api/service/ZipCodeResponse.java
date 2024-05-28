package com.example.rd_log_api.service;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZipCodeResponse {

        private String cep;
        private String logradouro;
        private String complemento;
        private String bairro;
        private String localidade; // City
        private String uf; // State




}

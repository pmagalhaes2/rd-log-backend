package com.example.rd_log_api.domain.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginAdminResponse {
    private String message;
    private Long administratorId;
    private String administratorName;
}
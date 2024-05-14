package com.example.rd_log_api.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Entity
@Getter
@Table(name = "logistic_companies")
@NoArgsConstructor
@AllArgsConstructor
public class LogisticCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cnpj;

    @Column(name="opening_hours")
    private Time opening_hours;

    @Column(name="closing_hours")
    private Time closing_hours;

    @Column(name="phone_number")
    private String phone_number;

    @Column(unique = true)
    private String email;

    @Column(name="accepts_dangerous_loads")
    private Boolean accepts_dangerous_loads = Boolean.FALSE;

    private String password;

    public LogisticCompany(Long id, String name, String cnpj, Time openingHours, Time closingHours, String phoneNumber, String email, Boolean acceptsDangerousLoads) {}
}

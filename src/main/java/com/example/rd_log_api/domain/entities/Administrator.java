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
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.sql.Time;
@Entity
@Getter
@Setter
@Table(name = "administrator")
@NoArgsConstructor
@AllArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CPF
    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    private String password;

    public Administrator(Long id, String name, String cpf, String email) {}
}


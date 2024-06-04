package com.example.rd_log_api.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@Entity
@Getter
@Setter
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
    private Time openingHours;

    @Column(name="closing_hours")
    private Time closingHours;

    @Column(name="phone_number")
    private String phoneNumber;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address_id", referencedColumnName = "id")
    private Address address;
}

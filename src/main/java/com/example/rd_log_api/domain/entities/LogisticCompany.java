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

    @OneToOne(orphanRemoval = true)
    @JoinTable(name = "logistic_companies_address",
            //joinColumns = @JoinColumn(name = "logisticCompany_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Address address;

    public LogisticCompany(Long id, String name, String cnpj, Time openingHours, Time closingHours, String phoneNumber, String email, String address) {}


}

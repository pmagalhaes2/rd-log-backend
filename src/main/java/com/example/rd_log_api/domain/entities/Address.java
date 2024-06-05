package com.example.rd_log_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "address")
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false)
    private String value;

    @Column
    private Integer number;

    @Column(nullable = false, length = 50)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 8)
    private String zipCode;

    @OneToOne(mappedBy = "address")
    @JsonManagedReference
    private LogisticCompany logisticCompany;

    public Address(Long id, String type, String value, Integer number, String city, String state, String zipCode) {
        this.id = id;
        this.type = type;
        this.value = value;
        this.number = number;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}

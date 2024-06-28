package com.example.rd_log_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    @JsonProperty("created_at")
    private Timestamp createdAt;

    @UpdateTimestamp
    @JsonProperty("updated_at")
    private Timestamp updatedAt;

    @JsonProperty("supplier_id")
    private Long supplierId;

    private String status;

    @ManyToOne
    @JoinColumn(name = "logistic_company_id", referencedColumnName = "id")
    @JsonBackReference(value = "logistic_companies")
    @JsonProperty("logistic_company")
    private LogisticCompany logisticCompany;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "origin_address")
    @JsonProperty("origin_address")
    private Address originAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "destination_address")
    @JsonProperty("destination_address")
    private Address destinationAddress;


}
package com.example.rd_log_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;

    private Long supplier_id;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "logistic_company_id", referencedColumnName = "id")
    @JsonBackReference(value = "logistic_companies")
    private LogisticCompany logistic_company = null;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "origin_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "origin_address")
    private Address origin_address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "destination_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "destination_address")
    private Address destination_address;

    public Orders(Long id, Timestamp createdAt, Timestamp updatedAt, Long supplierId, String status, Long logisticCompanyId, Address originAddress, Address destinationAddress) {
    }
}
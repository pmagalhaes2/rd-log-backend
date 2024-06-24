package com.example.rd_log_api.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private Timestamp created_at;

    private Timestamp updated_at;

    private Long supplier_id;

    private String status;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="origin_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "origin-address")
    private Address originAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="destination_address_id", referencedColumnName = "id")
    @JsonBackReference(value = "destination-address")
    private Address destinationAddress;

}

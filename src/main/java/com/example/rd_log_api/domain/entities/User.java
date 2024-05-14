package com.example.rd_log_api.domain.entities;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuarios")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", nullable = false, unique = true, length = 255) // Vamos manter usuário ou modificar pra e-mail? Confirmar com as meninas.
    private String username;

    @Column(name = "paswword", length = 8) //Vamos manter o tamanho 8?
    private String password;
}
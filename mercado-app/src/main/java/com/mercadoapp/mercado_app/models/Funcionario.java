package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "FUNCIONARIO")
public class Funcionario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeFunc;

    @Column(unique = true, nullable = false)
    private String cpfFunc;

    private String telFunc;
    private Integer idadeFunc;
}

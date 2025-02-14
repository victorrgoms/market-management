package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "COMPRA")
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_atend")
    private Atendimento atendimento;

    @ManyToOne
    @JoinColumn(name = "id_prod")
    private Produto produto;

    @Column(nullable = false)
    private Double valorTotal;

    @Column(nullable = false)
    private String formaPagam;

    private Integer quantProd;
    private String enderEntrega;
}

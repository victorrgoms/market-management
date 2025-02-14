package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeProd;

    @Column(nullable = false)
    private Double valorProd;

    private String fornecedor;
    private Date dataVencimento;

    // Relacionamento com Compra com exclusão em cascata
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras;
}

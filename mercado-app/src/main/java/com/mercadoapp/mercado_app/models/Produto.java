package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

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
}

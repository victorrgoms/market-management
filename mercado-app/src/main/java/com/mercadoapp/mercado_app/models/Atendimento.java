package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "ATENDIMENTO")
public class Atendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_merc")
    private Mercado mercado;

    @ManyToOne
    @JoinColumn(name = "id_client")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "id_func")
    private Funcionario funcionario;

    // Relacionamento com Compra com CascadeType.ALL para exclusão em cascata
    @OneToMany(mappedBy = "atendimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Compra> compras;
}

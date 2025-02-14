package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;

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
}

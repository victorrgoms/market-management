package com.mercadoapp.mercado_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

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

    // Relacionamento com Atendimento, com exclusão em cascata
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignorar o campo na serialização para evitar loop infinito
    private List<Atendimento> atendimentos;
}

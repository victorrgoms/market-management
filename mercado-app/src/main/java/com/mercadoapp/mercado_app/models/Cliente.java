package com.mercadoapp.mercado_app.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "CLIENTE", uniqueConstraints = @UniqueConstraint(columnNames = "cpfClient"))
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String cpfClient;

    @Column(nullable = false)
    private String nomeClient;

    private String nacionalidade;

    private Integer idadeClient;

    // Relacionamento com Atendimento, permitindo exclusão em cascata
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore // Ignorar o campo na serialização para evitar loop infinito
    private List<Atendimento> atendimentos;
}

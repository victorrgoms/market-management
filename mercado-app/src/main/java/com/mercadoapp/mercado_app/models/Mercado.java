package com.mercadoapp.mercado_app.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "MERCADO")
public class Mercado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String telMerc;
    private String nomeMerc;
    private String horaAbertura;
    private String enderMerc;

    // Relacionamento com Atendimento (com delete em cascade)
    @OneToMany(mappedBy = "mercado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Atendimento> atendimentos;
}

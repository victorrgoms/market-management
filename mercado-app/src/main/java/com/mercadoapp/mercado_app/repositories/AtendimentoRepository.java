package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {

    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS ATENDIMENTO (" +
            "Id SERIAL PRIMARY KEY, " +
            "Id_Merc INT REFERENCES MERCADO(Id_Merc) ON DELETE CASCADE, " +
            "Id_Client INT REFERENCES CLIENTE(Id_Client) ON DELETE CASCADE, " +
            "Id_Func INT REFERENCES FUNCIONARIO(Id_Func) ON DELETE CASCADE)", nativeQuery = true)
    void criarTabelaAtendimento();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO ATENDIMENTO (Id_Merc, Id_Client, Id_Func) VALUES " +
            "(1, 1, 1), " +
            "(2, 2, 2), " +
            "(3, 3, 1), " +
            "(4, 4, 3), " +
            "(1, 5, 2);",
            nativeQuery = true)
    void inserirAtendimentos();
}

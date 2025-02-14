package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.HistoricoCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface HistoricoCompraRepository extends JpaRepository<HistoricoCompra, Long> {
    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS HISTORICO_COMPRA (" +
            "Id_Historico SERIAL PRIMARY KEY, " +
            "Id_Compra INT, " +
            "Valor_Total DECIMAL(10,2), " +
            "Forma_Pagam VARCHAR(50), " +
            "Data_Ocorrencia TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
            "Operacao VARCHAR(10))", nativeQuery = true)
    void criarTabelaHistoricoCompra();

    @Modifying
    @Transactional
    @Query(value = "CREATE OR REPLACE FUNCTION funcao_historico_compra() " +
            "RETURNS TRIGGER AS $$ " +
            "BEGIN " +
            "    IF TG_OP = 'INSERT' THEN " +
            "        INSERT INTO HISTORICO_COMPRA (id_compra, valor_total, forma_pagam, operacao, data_ocorrencia) " +
            "        VALUES (NEW.id, NEW.valor_total, NEW.forma_pagam, 'INSERT', NOW()); " +
            "        RETURN NEW; " +
            "    ELSIF TG_OP = 'UPDATE' THEN " +
            "        INSERT INTO HISTORICO_COMPRA (id_compra, valor_total, forma_pagam, operacao, data_ocorrencia) " +
            "        VALUES (NEW.id, NEW.valor_total, NEW.forma_pagam, 'UPDATE', NOW()); " +
            "        RETURN NEW; " +
            "    ELSIF TG_OP = 'DELETE' THEN " +
            "        INSERT INTO HISTORICO_COMPRA (id_compra, valor_total, forma_pagam, operacao, data_ocorrencia) " +
            "        VALUES (OLD.id, OLD.valor_total, OLD.forma_pagam, 'DELETE', NOW()); " +
            "        RETURN OLD; " +
            "    END IF; " +
            "    RETURN NULL; " +
            "END; " +
            "$$ LANGUAGE plpgsql;", nativeQuery = true)
    void criarFuncaoHistoricoCompra();

    @Modifying
    @Transactional
    @Query(value = "DO $$ BEGIN " +
            "IF NOT EXISTS (SELECT 1 FROM pg_trigger WHERE tgname = 'trg_historico_compra') THEN " +
            "CREATE TRIGGER trg_historico_compra " +
            "AFTER INSERT OR UPDATE OR DELETE ON compra " +
            "FOR EACH ROW " +
            "EXECUTE FUNCTION funcao_historico_compra(); " +
            "END IF; " +
            "END $$;", nativeQuery = true)
    void criarGatilhoHistoricoCompra();
}

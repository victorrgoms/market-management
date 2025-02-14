package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Compra;
import com.mercadoapp.mercado_app.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {

    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS COMPRA (" +
            "Id_Compra SERIAL PRIMARY KEY, " +
            "Id_Atend INT REFERENCES ATENDIMENTO(Id_Atend) ON DELETE CASCADE, " +
            "Id_Prod INT REFERENCES PRODUTO(Id_Prod) ON DELETE CASCADE, " +
            "Valor_Total DECIMAL(10,2) NOT NULL, " +
            "Forma_Pagam VARCHAR(50) NOT NULL, " +
            "Quant_Prod INT, " +
            "Ender_Entrega VARCHAR(255))", nativeQuery = true)
    void criarTabelaCompra();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO COMPRA (Id_Atend, Id_Prod, Valor_Total, Forma_Pagam, Quant_Prod, Ender_Entrega) VALUES " +
            "(1, 1, 40.00, 'Cartão de Crédito', 2, 'Rua A, 123'), " +  // Carlos compra Arroz
            "(2, 2, 20.00, 'Dinheiro', 2, 'Avenida B, 456'), " +  // Maria compra Feijão
            "(3, 3, 10.00, 'Cartão de Débito', 2, 'Rua C, 789'), " +  // Pedro compra Macarrão
            "(4, 4, 17.00, 'Dinheiro', 2, 'Avenida D, 321'), " +  // Larissa compra Óleo de Soja
            "(5, 5, 6.40, 'Cartão de Crédito', 2, 'Rua A, 123');",   // Rodrigo compra Açúcar
            nativeQuery = true)
    void inserirCompras();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM COMPRA WHERE Id_Compra = ?1", nativeQuery = true)
    void deletarCompra(Long idCompra);

    @Query(value = "SELECT c.Id, cl.Nome_Client, p.Nome_Prod, c.Valor_Total, c.Forma_Pagam " +
            "FROM COMPRA c " +
            "JOIN ATENDIMENTO a ON c.Id_Atend = a.Id " + // A tabela ATENDIMENTO usa Id_Atend
            "JOIN CLIENTE cl ON a.Id_Client = cl.Id " + // A tabela CLIENTE usa Id_Client
            "JOIN PRODUTO p ON c.Id_Prod = p.Id", nativeQuery = true)
    List<Object[]> listarComprasDetalhadas();

    @Query(value = "SELECT p.Nome_Prod, m.Nome_Merc " +
            "FROM PRODUTO p " +
            "JOIN COMPRA c ON p.id = c.Id_Prod " + // Usando id para Produto
            "JOIN ATENDIMENTO a ON c.Id_Atend = a.ID " + // Usando id para Atendimento
            "JOIN MERCADO m ON a.Id_Merc = m.id", nativeQuery = true)
    List<Object[]> listarProdutosComMercado();

    @Query(value = "SELECT * FROM PRODUTO WHERE Data_Vencimento <= CURRENT_DATE + INTERVAL '30 days'", nativeQuery = true)
    List<Produto> buscarProdutosProximosDoVencimento();
}

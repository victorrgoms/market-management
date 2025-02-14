package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS PRODUTO (" +
            "Id_Prod SERIAL PRIMARY KEY, " +
            "Nome_Prod VARCHAR(100) NOT NULL, " +
            "Valor_Prod DECIMAL(10,2) NOT NULL, " +
            "Fornecedor VARCHAR(100), " +
            "Data_Vencimento DATE)", nativeQuery = true)
    void criarTabelaProduto();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO PRODUTO (Nome_Prod, Valor_Prod, Fornecedor, Data_Vencimento) VALUES " +
            "('Arroz', 20.00, 'Fornecedor X', '2025-12-31'), " +
            "('Feijão', 10.00, 'Fornecedor Y', '2025-10-30'), " +
            "('Macarrão', 5.00, 'Fornecedor Z', '2025-11-30'), " +
            "('Óleo de Soja', 8.50, 'Fornecedor Y', '2025-09-15'), " +
            "('Açúcar', 3.20, 'Fornecedor X', '2025-10-10'), " +
            "('Sal', 2.00, 'Fornecedor W', '2025-12-31');", nativeQuery = true)
    void inserirProdutos();


    @Query(value = "SELECT Nome_Prod FROM PRODUTO p WHERE Valor_Prod > ALL (SELECT Valor_Prod FROM PRODUTO WHERE Fornecedor = 'Fornecedor_X')", nativeQuery = true)
    List<String> buscarProdutoMaiorQueTodosDeFornecedor();

    @Query(value = "SELECT Nome_Prod, Valor_Prod FROM PRODUTO ORDER BY Valor_Prod ASC", nativeQuery = true)
    List<Object[]> listarProdutosOrdenadosPorValorCrescente();


}

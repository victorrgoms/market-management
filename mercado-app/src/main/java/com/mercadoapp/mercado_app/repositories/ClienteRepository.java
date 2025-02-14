package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS CLIENTE (" +
            "Id SERIAL PRIMARY KEY, " +
            "Cpf_Client VARCHAR(14) UNIQUE NOT NULL, " +
            "Nome_Client VARCHAR(100) NOT NULL, " +
            "Nacionalidade VARCHAR(50), " +
            "Idade_Client INT)", nativeQuery = true)
    void criarTabelaCliente();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO CLIENTE (id, Cpf_Client, Nome_Client, Nacionalidade, Idade_Client) VALUES " +
            "(1, '123.456.789-00', 'Carlos Silva', 'Brasileiro', 30), " +
            "(2, '987.654.321-00', 'Maria Souza', 'Brasileira', 25), " +
            "(3, '111.222.333-45', 'Pedro Alves', 'Brasileiro', 40), " +
            "(4, '222.333.444-56', 'Larissa Ferreira', 'Brasileira', 28), " +
            "(5, '333.444.555-67', 'Rodrigo Lima', 'Brasileiro', 35) " +
            "ON CONFLICT (id) DO NOTHING;", nativeQuery = true)
    void inserirClientes();

    @Modifying
    @Transactional
    @Query("UPDATE Cliente c SET c.idadeClient = :idade WHERE c.cpfClient = :cpf")
    void atualizarIdadeCliente(@Param("idade") int idade, @Param("cpf") String cpf);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM CLIENTE WHERE Cpf_Client = ?1", nativeQuery = true)
    void deletarCliente(String cpfClient);

    @Query(value = "SELECT * FROM CLIENTE WHERE LOWER(Nome_Client) LIKE LOWER('joão%')", nativeQuery = true)
    List<Cliente> buscarClientePorNomeSubstring();

    @Query(value = "SELECT c.Nome_Client, COUNT(comp.Id) AS Total_Compras, SUM(comp.Valor_Total) AS Valor_Total_Compras " +
            "FROM CLIENTE c " +
            "JOIN ATENDIMENTO a ON c.Id = a.Id_Client " +
            "JOIN COMPRA comp ON a.Id = comp.Id_Atend " +
            "GROUP BY c.Nome_Client " +
            "HAVING SUM(comp.Valor_Total) > 2", nativeQuery = true)
    List<Object[]> listarComprasPorClienteComValorTotalMaiorQue();

}

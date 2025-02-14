package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS FUNCIONARIO (" +
            "Id_Func SERIAL PRIMARY KEY, " +
            "Nome_Func VARCHAR(100) NOT NULL, " +
            "Cpf_Func VARCHAR(14) UNIQUE NOT NULL, " +
            "Tel_Func VARCHAR(15), " +
            "Idade_Func INT)", nativeQuery = true)
    void criarTabelaFuncionario();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO FUNCIONARIO (Nome_Func, Cpf_Func, Tel_Func, Idade_Func) VALUES " +
            "('João Pereira', '111.222.333-44', '11987654321', 40), " +
            "('Ana Oliveira', '555.666.777-88', '11912345678', 35), " +
            "('Mariana Santos', '444.555.666-77', '11999999999', 30), " +
            "('Lucas Pereira', '888.999.000-11', '11888888888', 32), " +
            "('Fernanda Costa', '777.666.555-44', '11777777777', 29) " +
            "ON CONFLICT (Cpf_Func) DO NOTHING;", nativeQuery = true)
    void inserirFuncionarios();

    @Modifying
    @Transactional
    @Query("UPDATE Funcionario f SET f.telFunc = :telefone WHERE f.cpfFunc = :cpf")
    void atualizarTelefoneFuncionario(@Param("telefone") String telefone, @Param("cpf") String cpf);

    @Query(value = "SELECT DISTINCT f.Nome_Func " +
            "FROM FUNCIONARIO f " +
            "JOIN ATENDIMENTO a ON f.id = a.Id_Func", nativeQuery = true)
    List<String> listarFuncionariosQueAtenderamClientes();

    @Query(value = "SELECT f.Nome_Func, COUNT(a.ID) AS Total_Atendimentos " +
            "FROM FUNCIONARIO f " +
            "JOIN ATENDIMENTO a ON f.id = a.Id_Func " + // Usando id para Funcionario
            "GROUP BY f.Nome_Func", nativeQuery = true)
    List<Object[]> listarAtendimentosPorFuncionario();

    @Query(value = "SELECT * FROM FUNCIONARIO WHERE LOWER(Nome_Func) LIKE LOWER('ana%')", nativeQuery = true)
    List<Funcionario> buscarFuncionarioPorNomeSubstring();

    @Query(value = "SELECT Nome_Func FROM FUNCIONARIO f WHERE Idade_Func > ANY (SELECT Idade_Client FROM CLIENTE)", nativeQuery = true)
    List<String> buscarFuncionarioIdadeMaiorQueClientes();

}
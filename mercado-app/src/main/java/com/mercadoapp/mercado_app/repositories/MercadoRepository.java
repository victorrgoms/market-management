package com.mercadoapp.mercado_app.repositories;

import com.mercadoapp.mercado_app.models.Mercado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MercadoRepository extends JpaRepository<Mercado, Long> {

    @Modifying
    @Transactional
    @Query(value = "CREATE TABLE IF NOT EXISTS MERCADO (" +
            "Id_Merc SERIAL PRIMARY KEY, " +
            "Tel_Merc VARCHAR(15), " +
            "Nome_Merc VARCHAR(100) NOT NULL, " +
            "Hora_abertura TIME, " +
            "Ender_merc VARCHAR(255))", nativeQuery = true)
    void criarTabelaMercado();

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO MERCADO (Tel_Merc, Nome_Merc, Hora_abertura, Ender_merc) VALUES " +
            "('11987654321', 'Mercado Central', '08:00:00', 'Rua A, 123'), " +
            "('11912345678', 'Supermercado B', '07:30:00', 'Avenida B, 456'), " +
            "('11900000000', 'Mercadinho Verde', '08:30:00', 'Rua C, 789'), " +
            "('11876543210', 'Supermercado X', '06:00:00', 'Avenida D, 321')", nativeQuery = true)
    void inserirMercados();

    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE MERCADO RESTART IDENTITY CASCADE;", nativeQuery = true)
    void truncarMercado();

    @Query(value = "SELECT * FROM MERCADO WHERE LOWER(Nome_Merc) LIKE LOWER('%mercado%')", nativeQuery = true)
    List<Mercado> buscarMercadosPorNomeSubstring();


}

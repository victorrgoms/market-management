package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.models.HistoricoCompra;
import com.mercadoapp.mercado_app.repositories.HistoricoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoCompraService {

    @Autowired
    private HistoricoCompraRepository historicoCompraRepository;

    public void inicializarTabelaHistoricoCompra() {
        historicoCompraRepository.criarTabelaHistoricoCompra();
    }

    public HistoricoCompra salvar(HistoricoCompra historicoCompra) {
        return historicoCompraRepository.save(historicoCompra);
    }

    public Optional<HistoricoCompra> buscarPorId(Long id) {
        return historicoCompraRepository.findById(id);
    }

    public List<HistoricoCompra> listarTodos() {
        return historicoCompraRepository.findAll();
    }

    public void excluir(Long id) {
        historicoCompraRepository.deleteById(id);
    }
}

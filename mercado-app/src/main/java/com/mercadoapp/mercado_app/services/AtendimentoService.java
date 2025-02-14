package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.models.Atendimento;
import com.mercadoapp.mercado_app.repositories.AtendimentoRepository;
import com.mercadoapp.mercado_app.repositories.ClienteRepository;
import com.mercadoapp.mercado_app.repositories.FuncionarioRepository;
import com.mercadoapp.mercado_app.repositories.MercadoRepository;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AtendimentoService {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private MercadoRepository mercadoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EntityManager entityManager;

    public AtendimentoService(AtendimentoRepository atendimentoRepository) {
        this.atendimentoRepository = atendimentoRepository;
    }

    @Transactional
    public void inicializarBanco() {
        // Primeiro, garanta que as tabelas já foram criadas (se precisar)
        atendimentoRepository.criarTabelaAtendimento();

        mercadoRepository.inserirMercados();
        clienteRepository.inserirClientes();
        funcionarioRepository.inserirFuncionarios();

        atendimentoRepository.inserirAtendimentos();

    }

    @PostConstruct
    public void init() {
        inicializarBanco();
        inicializarTabelaAtendimento();
        inicializarAtendimentos();
    }


    public void inicializarTabelaAtendimento() {
        atendimentoRepository.criarTabelaAtendimento();  // Criar Tabela Atendimento
    }

    public void inicializarAtendimentos() {
        atendimentoRepository.inserirAtendimentos();  // Inserir Atendimentos
    }

    public List<Atendimento> listarTodos() {
        return atendimentoRepository.findAll();
    }

    public Optional<Atendimento> buscarPorId(Long id) {
        return atendimentoRepository.findById(id);
    }

    public Atendimento salvar(Atendimento atendimento) {
        return atendimentoRepository.save(atendimento);
    }

    public void deletar(Long id) {
        atendimentoRepository.deleteById(id);
    }
}

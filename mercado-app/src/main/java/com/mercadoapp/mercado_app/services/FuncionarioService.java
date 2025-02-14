package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Funcionario;
import com.mercadoapp.mercado_app.repositories.FuncionarioRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    @Autowired
    private final FuncionarioRepository funcionarioRepository;

    @PostConstruct
    public void init() {
        inicializarTabelaFuncionario();
        inicializarFuncionarios();
    }

    public void inicializarTabelaFuncionario() {
        funcionarioRepository.criarTabelaFuncionario();
    }

    public void inicializarFuncionarios() {
        funcionarioRepository.inserirFuncionarios();
    }

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public Funcionario atualizar(Funcionario funcionario, Long id) {
        Optional<Funcionario> funcionarioDb = funcionarioRepository.findById(id);
        if (funcionarioDb.isPresent()) {
            Funcionario funcionario1 = funcionarioDb.get();
            funcionario1.setIdadeFunc(funcionario.getIdadeFunc()); // Exemplo de atualização
            funcionario1.setTelFunc(funcionario.getTelFunc());
            funcionario1.setNomeFunc(funcionario.getNomeFunc());
            return funcionarioRepository.save(funcionario1);
        } else {
            throw new NotFoundException("Funcionário não encontrado!");
        }
    }

}

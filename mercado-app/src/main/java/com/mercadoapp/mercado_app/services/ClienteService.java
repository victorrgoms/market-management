package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Cliente;
import com.mercadoapp.mercado_app.repositories.ClienteRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @PostConstruct
    public void initCliente() {
        inicializarTabelaCliente();
        inicializarClientes();
    }

    public void inicializarTabelaCliente() {
        clienteRepository.criarTabelaCliente();  // Criar Tabela Cliente
    }

    public void inicializarClientes() {
        clienteRepository.inserirClientes();  // Inserir Clientes
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar(Long id) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if (clienteDb.isPresent()) {
            clienteRepository.delete(clienteDb.get());
        } else {
            throw new NotFoundException("Cliente não encontrado!");
        }
    }


    public Cliente atualizar(Cliente cliente, Long id) {
        Optional<Cliente> clienteDb = clienteRepository.findById(id);
        if (clienteDb.isPresent()) {
            Cliente cliente1 = clienteDb.get();
            cliente1.setIdadeClient(cliente.getIdadeClient());
            cliente1.setNomeClient(cliente.getNomeClient());
            cliente1.setNacionalidade(cliente.getNacionalidade());
            return clienteRepository.save(cliente1);
        } else {
            throw new NotFoundException("Cliente não encontrado!");
        }
    }
}

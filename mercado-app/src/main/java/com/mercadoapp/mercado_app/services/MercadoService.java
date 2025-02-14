package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Mercado;
import com.mercadoapp.mercado_app.repositories.MercadoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MercadoService {

    @Autowired
    private final MercadoRepository mercadoRepository;

    public MercadoService(MercadoRepository mercadoRepository) {
        this.mercadoRepository = mercadoRepository;
    }

    @PostConstruct
    public void initMercado() {
        inicializarTabelaMercado();
        resetarMercado();
        inicializarMercados();
    }

    public void resetarMercado() {
        mercadoRepository.truncarMercado();  // Resetar Mercado
    }

    public void inicializarTabelaMercado() {
        mercadoRepository.criarTabelaMercado();  // Criar Tabela Mercado
    }

    public void inicializarMercados() {
        mercadoRepository.inserirMercados();  // Inserir dados Mercado
    }

    public List<Mercado> listarTodos() {
        return mercadoRepository.findAll();
    }

    public Optional<Mercado> buscarPorId(Long id) {
        return mercadoRepository.findById(id);
    }

    public Mercado salvar(Mercado mercado) {
        return mercadoRepository.save(mercado);
    }

    public void deletar(Long id) {
        mercadoRepository.deleteById(id);
    }

    public Mercado atualizar(Mercado mercado, Long id) {
        Optional<Mercado> mercadoDb = mercadoRepository.findById(id);
        if (mercadoDb.isPresent()) {
            Mercado mercadoExistente = mercadoDb.get();
            mercadoExistente.setTelMerc(mercado.getTelMerc()); // Atualiza o telefone
            mercadoExistente.setNomeMerc(mercado.getNomeMerc()); // Atualiza o nome
            mercadoExistente.setHoraAbertura(mercado.getHoraAbertura()); // Atualiza a hora de abertura
            mercadoExistente.setEnderMerc(mercado.getEnderMerc()); // Atualiza o endereço
            return mercadoRepository.save(mercadoExistente); // Salva as alterações
        } else {
            throw new NotFoundException("Mercado não encontrado!");
        }
    }
}

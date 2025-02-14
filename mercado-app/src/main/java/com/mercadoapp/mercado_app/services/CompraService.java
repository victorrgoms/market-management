package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Compra;
import com.mercadoapp.mercado_app.repositories.CompraRepository;
import com.mercadoapp.mercado_app.repositories.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private final CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CompraService(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    public void inicializarBanco() {
        // Primeiro, garanta que as tabelas já foram criadas (se precisar)
        compraRepository.criarTabelaCompra();

        produtoRepository.inserirProdutos();

        compraRepository.inserirCompras();
    }

    @PostConstruct
    public void init() {
        inicializarBanco();
    }


    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    public Optional<Compra> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public Compra salvar(Compra compra) {
        return compraRepository.save(compra);
    }

    public void deletar(Long id) {
        compraRepository.deleteById(id);
    }

    public Compra atualizar(Compra compra, Long id) {
        Optional<Compra> compraDb = compraRepository.findById(id);
        if (compraDb.isPresent()) {
            Compra compraExistente = compraDb.get();
            compraExistente.setValorTotal(compra.getValorTotal()); // Atualiza o valor total
            compraExistente.setFormaPagam(compra.getFormaPagam()); // Atualiza a forma de pagamento
            compraExistente.setQuantProd(compra.getQuantProd()); // Atualiza a quantidade de produtos
            compraExistente.setEnderEntrega(compra.getEnderEntrega()); // Atualiza o endereço de entrega
            return compraRepository.save(compraExistente); // Salva as mudanças
        } else {
            throw new NotFoundException("Compra não encontrada!");
        }
    }

}

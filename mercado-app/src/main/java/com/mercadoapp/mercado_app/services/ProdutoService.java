package com.mercadoapp.mercado_app.services;

import com.mercadoapp.mercado_app.exception.NotFoundException;
import com.mercadoapp.mercado_app.models.Produto;
import com.mercadoapp.mercado_app.repositories.ProdutoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @PostConstruct
    public void init() {
        inicializarTabelaProduto();
        inicializarProdutos();
    }

    public void inicializarTabelaProduto() {
        produtoRepository.criarTabelaProduto();  // Criar Tabela Produto
    }

    public void inicializarProdutos() {
        produtoRepository.inserirProdutos();  // Inserir Produtos
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public void deletar(Long id) {
        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Produto produto, Long id) {
        Optional<Produto> produtoDb = produtoRepository.findById(id);
        if (produtoDb.isPresent()) {
            Produto produtoExistente = produtoDb.get();
            produtoExistente.setNomeProd(produto.getNomeProd()); // Atualiza o nome do produto
            produtoExistente.setValorProd(produto.getValorProd()); // Atualiza o valor do produto
            produtoExistente.setFornecedor(produto.getFornecedor()); // Atualiza o fornecedor
            produtoExistente.setDataVencimento(produto.getDataVencimento()); // Atualiza a data de vencimento
            return produtoRepository.save(produtoExistente); // Salva as mudanças
        } else {
            throw new NotFoundException("Produto não encontrado!");
        }
    }

}

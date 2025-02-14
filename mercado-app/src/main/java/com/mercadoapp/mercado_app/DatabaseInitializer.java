package com.mercadoapp.mercado_app;

import com.mercadoapp.mercado_app.models.*;
import com.mercadoapp.mercado_app.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DatabaseInitializer {

    @Autowired
    private AtendimentoRepository atendimentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private HistoricoCompraRepository historicoCompraRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private MercadoRepository mercadoRepository;

    @PostConstruct
    public void init() {

        // Inicializa Atendimento
        atendimentoRepository.criarTabelaAtendimento();
        atendimentoRepository.inserirAtendimentos();

        // Inicializa Compra
        compraRepository.criarTabelaCompra();
        // Inicializa Historico_Compra
        historicoCompraRepository.criarTabelaHistoricoCompra();
        configurarHistoricoCompra();

        // Teste do gatilho de Compra
        testarGatilhoCompra();
        compraRepository.inserirCompras();

        // Atualiza Cliente (Idade)
        // clienteRepository.atualizarIdadeCliente(31, "123.456.789-00");

        // Atualiza Funcionario (Telefone)
        // funcionarioRepository.atualizarTelefoneFuncionario("11955555555", "111.222.333-44");

        // Deleta Compra (Id_Compra = 1)
        // compraRepository.deletarCompra(1L);

        // Deleta Cliente (Cpf_Client = '987.654.321-00')
        // clienteRepository.deletarCliente("987.654.321-00");

        // Listar todas as compras com detalhes de Cliente e Produto
        listarComprasDetalhadas();

        // Listar os funcionários que atenderam clientes
        listarFuncionariosQueAtenderamClientes();

        // Listar todos os produtos junto com o mercado que os vende
        listarProdutosComMercado();

        // Listar atendimentos por funcionário
        listarAtendimentosPorFuncionario();

        // Buscar produtos próximos do vencimento
        buscarProdutosProximosDoVencimento();

        // Buscar mercados por nome com substring
        buscarMercadosPorNomeSubstring();

        // Buscar funcionários por nome com substring
        buscarFuncionariosPorNomeSubstring();

        // Buscar clientes por nome com substring
        buscarClientesPorNomeSubstring();

        // Buscar produto maior que todos os de um fornecedor específico
        buscarProdutoMaiorQueFornecedorX();

        // Buscar funcionários com idade maior que qualquer cliente
        buscarFuncionarioIdadeMaiorQueClientes();

        // Buscar clientes com total de compras maior que um valor específico
        listarComprasPorClienteComValorTotalMaiorQue();

        // Buscar produtos ordenados por valor
        listarProdutosOrdenadosPorValorCrescente();




    }

    private void listarComprasDetalhadas() {
        List<Object[]> comprasDetalhadas = compraRepository.listarComprasDetalhadas();
        for (Object[] compra : comprasDetalhadas) {
            System.out.println("Id_Compra: " + compra[0] +
                    ", Nome_Client: " + compra[1] +
                    ", Nome_Prod: " + compra[2] +
                    ", Valor_Total: " + compra[3] +
                    ", Forma_Pagam: " + compra[4]);
        }
    }

    private void listarFuncionariosQueAtenderamClientes() {
        List<String> funcionarios = funcionarioRepository.listarFuncionariosQueAtenderamClientes();
        for (String funcionario : funcionarios) {
            System.out.println("Funcionario: " + funcionario);
        }
    }

    private void listarProdutosComMercado() {
        List<Object[]> produtosComMercado = compraRepository.listarProdutosComMercado();
        for (Object[] produto : produtosComMercado) {
            System.out.println("Nome_Prod: " + produto[0] + ", Nome_Merc: " + produto[1]);
        }
    }

    private void listarAtendimentosPorFuncionario() {
        List<Object[]> atendimentosPorFuncionario = funcionarioRepository.listarAtendimentosPorFuncionario();
        for (Object[] atendimento : atendimentosPorFuncionario) {
            System.out.println("Funcionario: " + atendimento[0] + ", Total_Atendimentos: " + atendimento[1]);
        }
    }

    private void buscarProdutosProximosDoVencimento() {
        List<Produto> produtosProximos = compraRepository.buscarProdutosProximosDoVencimento();
        for (Produto produto : produtosProximos) {
            System.out.println("Produto: " + produto.getNomeProd() + ", Data_Vencimento: " + produto.getDataVencimento());
        }
    }

    private void buscarMercadosPorNomeSubstring() {
        List<Mercado> mercados = mercadoRepository.buscarMercadosPorNomeSubstring();
        for (Mercado mercado : mercados) {
            System.out.println("Mercado: " + mercado.getNomeMerc());
        }
    }

    private void buscarFuncionariosPorNomeSubstring() {
        List<Funcionario> funcionarios = funcionarioRepository.buscarFuncionarioPorNomeSubstring();
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Funcionario: " + funcionario.getNomeFunc());
        }
    }

    private void buscarClientesPorNomeSubstring() {
        List<Cliente> clientes = clienteRepository.buscarClientePorNomeSubstring();
        for (Cliente cliente : clientes) {
            System.out.println("Cliente: " + cliente.getNomeClient());
        }
    }

    private void buscarProdutoMaiorQueFornecedorX() {
        List<String> produtos = produtoRepository.buscarProdutoMaiorQueTodosDeFornecedor();
        for (String produto : produtos) {
            System.out.println("Produto: " + produto);
        }
    }

    private void buscarFuncionarioIdadeMaiorQueClientes() {
        List<String> funcionarios = funcionarioRepository.buscarFuncionarioIdadeMaiorQueClientes();
        for (String funcionario : funcionarios) {
            System.out.println("Funcionario: " + funcionario);
        }
    }

    private void listarComprasPorClienteComValorTotalMaiorQue() {
        List<Object[]> compras = clienteRepository.listarComprasPorClienteComValorTotalMaiorQue();
        for (Object[] compra : compras) {
            System.out.println("Cliente: " + compra[0] + ", Total Compras: " + compra[1] + ", Valor Total Compras: " + compra[2]);
        }
    }

    private void listarProdutosOrdenadosPorValorCrescente() {
        List<Object[]> produtos = produtoRepository.listarProdutosOrdenadosPorValorCrescente();
        for (Object[] produto : produtos) {
            System.out.println("Produto: " + produto[0] + ", Valor: " + produto[1]);
        }
    }

    private void configurarHistoricoCompra(){
        historicoCompraRepository.criarFuncaoHistoricoCompra();
        historicoCompraRepository.criarGatilhoHistoricoCompra();
    }

    // Simula as operações de INSERT, UPDATE, DELETE para disparar o gatilho
    private void testarGatilhoCompra() {
        // Simula uma inserção que vai disparar o gatilho
        Compra novaCompra = new Compra();
        novaCompra.setValorTotal(100.0);
        novaCompra.setFormaPagam("Cartão");
        compraRepository.save(novaCompra);  // Isso dispara o gatilho de INSERT

        // Simula uma atualização que vai disparar o gatilho
        novaCompra.setValorTotal(150.0);
        compraRepository.save(novaCompra);  // Isso dispara o gatilho de UPDATE

        // Simula uma exclusão que vai disparar o gatilho
        compraRepository.delete(novaCompra);  // Isso dispara o gatilho de DELETE
    }
}

package com.mercadoapp.mercado_app.models;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORICO_COMPRA")
public class HistoricoCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_Historico")
    private Long idHistorico;

    @Column(name = "Id_Compra", nullable = false)
    private Integer idCompra;

    @Column(name = "Valor_Total", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "Forma_Pagam", nullable = false, length = 50)
    private String formaPagam;

    @Column(name = "Data_Ocorrencia", nullable = false)
    private LocalDateTime dataOcorrencia;

    @Column(name = "Operacao", nullable = false, length = 10)
    private String operacao;

    // Getters and Setters
    public Long getIdHistorico() {
        return idHistorico;
    }

    public void setIdHistorico(Long idHistorico) {
        this.idHistorico = idHistorico;
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getFormaPagam() {
        return formaPagam;
    }

    public void setFormaPagam(String formaPagam) {
        this.formaPagam = formaPagam;
    }

    public LocalDateTime getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(LocalDateTime dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getOperacao() {
        return operacao;
    }

    public void setOperacao(String operacao) {
        this.operacao = operacao;
    }
}

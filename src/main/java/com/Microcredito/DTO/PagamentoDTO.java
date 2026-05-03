package com.Microcredito.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class PagamentoDTO {

    private Long contratoId;
    private Long parcelaId;
 
    private BigDecimal valorTotalPago;

    private String metodoPagamento; // ENUM como string
    private String transactionId;
    private String gatewayResposta;

    private String statusPagamento;

    private String codigoAutorizacao;
    private String nsu;
}

package com.Microcredito.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class AnaliseCreditoDTO {

    private Long solicitacaoId;
    private Long analistaId;

    private Integer scoreAnalisado;
    private BigDecimal capRenda;
    private BigDecimal comprometimentoRenda;

    private Boolean parecerFavoravel;
    private String parecerTexto;

    private BigDecimal limiteCreditoCalculado;
    private BigDecimal taxaJurosOfertada;

    private Boolean restricoesEncontradas;
    private String descricaoRestricoes;
}

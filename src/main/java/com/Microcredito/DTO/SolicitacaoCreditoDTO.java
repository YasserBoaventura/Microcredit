package com.Microcredito.DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data 
public class SolicitacaoCreditoDTO {
	
	private Long clienteId;
    private BigDecimal valorSolicitado;
    private Integer quantidadeParcelas;
    private BigDecimal taxaJurosMensal;
    private String finalidade;
    private Integer mesPrazo; 
   
    private String protocolo;

}

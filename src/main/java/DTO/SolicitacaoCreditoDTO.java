package DTO;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class SolicitacaoCreditoDTO {
    private Long clienteId;
    private BigDecimal valorSolicitado;
    private Integer quantidadeParcelas;
    private BigDecimal taxaJurosMensal;
    private String finalidade;

   
    private String protocolo;

}

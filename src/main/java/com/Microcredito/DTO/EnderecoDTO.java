package com.Microcredito.DTO;

import lombok.Data;

@Data 
public class EnderecoDTO {
	  private Long clienteId;
	    private String cep;
	    private String logradouro;
	    private String numero;
	    private String complemento;
	    private String bairro;
	    private String cidade;
	    private String estado;
	    private String tipoEndereco;
	    private Boolean principal;

}

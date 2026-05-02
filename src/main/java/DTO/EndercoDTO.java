package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor 
public class EndercoDTO {
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

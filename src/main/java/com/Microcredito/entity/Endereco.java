package com.Microcredito.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "endereco")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Endereco {

	   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(length = 10)
    private String cep;
    
    @Column(length = 150)
    private String logradouro;
    
    @Column(length = 10)
    private String numero;
    
    @Column(length = 100)
    private String complemento;
    
    @Column(length = 100)
    private String bairro;
    
    @Column(length = 100)
    private String cidade;
    
    @Column(length = 2)
    private String estado;
    
    @Column(name = "tipo_endereco", length = 30)
    private String tipoEndereco; // RESIDENCIAL, COMERCIAL, COBRANCA
    
    @Column(name = "principal")
    private Boolean principal = false;
}

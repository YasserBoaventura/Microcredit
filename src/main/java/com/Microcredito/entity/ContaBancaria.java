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
@Table(name = "conta_bancaria")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaBancaria {
	  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Column(length = 50)
    private String banco;
    
    @Column(length = 10)
    private String agencia;
    
    @Column(length = 20)
    private String conta;
    
    @Column(length = 2)
    private String digito;
    
    @Column(name = "tipo_conta", length = 20)
    private String tipoConta; // CORRENTE, POUPANCA, SALARIO
    
    @Column(name = "chave_pix", length = 100)
    private String chavePix;
    
    @Column(name = "principal")
    private Boolean principal = false;
    
    @Column(name = "ativo")
    private Boolean ativo = true;


	
}

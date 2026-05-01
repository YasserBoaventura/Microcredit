package com.Microcredito.entity;

import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DialectOverride.SQLRestriction;
import org.hibernate.annotations.UpdateTimestamp;

import com.Microcredito.auth.Usuario;
import com.Microcredito.enums.TipoPessoa;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
@AllArgsConstructor
@Builder
@Entity
@Table(name ="cliente")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;
    
    @Column(name = "nome_razao_social", nullable = false, length = 200)
    private String nomeRazaoSocial;
    
    @Column(name = "nome_fantasia", length = 100)
    private String nomeFantasia;
    
    @Column(unique = true, nullable = false, length = 18)
    private String cpfCnpj;
    
    @Column(unique = true, nullable = false, length = 150)
    private String email;
    
    @Column(length = 20)
    private String telefone;
    
    @Column(length = 20)
    private String celular;
    
    @Column(name = "data_nascimento_fundacao")
    private LocalDate dataNascimentoFundacao;
    
    @Column(name = "renda_mensal", precision = 12, scale = 2)
    private BigDecimal rendaMensal;
    
    @Column(name = "score_credito")
    private Integer scoreCredito;
    
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @CreationTimestamp
    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;
    
    @UpdateTimestamp
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;
    
    @Column(columnDefinition = "TEXT")
    private String observacao;
    
    // Relacionamentos
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Endereco> enderecos = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ContaBancaria> contasBancarias = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Documento> documentos = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<SolicitacaoCredito> solicitacoes = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private List<Contrato> contratos = new ArrayList<>();
    
    @JsonIgnore
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;
    
    // Métodos helpers
    public void addEndereco(Endereco endereco) {
        enderecos.add(endereco);
        endereco.setCliente(this);
    }
    
    public void addContaBancaria(ContaBancaria conta) {
        contasBancarias.add(conta);
        conta.setCliente(this);
    }


	

}

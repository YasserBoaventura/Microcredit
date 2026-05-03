package com.Microcredito.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.auth.Usuario;
import com.Microcredito.enums.StatusSolicitacao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Index;
@Entity
@Table(name = "solicitacao_credito", indexes = {
    @Index(name = "idx_solicitacao_cliente", columnList = "cliente_id"),
    @Index(name = "idx_solicitacao_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SolicitacaoCredito {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cliente_id", nullable = false)
	    @JsonIgnore
	    private Cliente cliente;
	    
	    @Column(name = "valor_solicitado", nullable = false, precision = 12, scale = 2)
	    private BigDecimal valorSolicitado;
	    
	    @Column(name = "valor_aprovado", precision = 12, scale = 2)
	    private BigDecimal valorAprovado;
	    
	    @Column(name = "quantidade_parcelas", nullable = false)
	    private Integer quantidadeParcelas;
	    
	    @Column(name ="prozo_Meses")
	    private Integer prazoMeses;
	    
	    @Column(name = "taxa_juros_mensal", precision = 5, scale = 4)
	    private BigDecimal taxaJurosMensal;
	    
	    @Column(columnDefinition = "TEXT")
	    private String finalidade;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private StatusSolicitacao status;
	    
	    @CreationTimestamp
	    @Column(name = "data_solicitacao", updatable = false)
	    private LocalDateTime dataSolicitacao;
	    
	    @Column(name = "data_resposta")
	    private LocalDateTime dataResposta;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "analista_id")
	    @JsonIgnore
	    private Usuario analista;
	    
	    @Column(name = "motivo_reprovacao", columnDefinition = "TEXT")
	    private String motivoReprovacao;
	    
	    @Column(unique = true, length = 50)
	    private String protocolo;
	    
	    // Relacionamentos
	    @OneToOne(mappedBy = "solicitacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore
	    private AnaliseCredito analiseCredito;
	    
	    @OneToOne(mappedBy = "solicitacao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    @JsonIgnore
	    private Contrato contrato;
		
}

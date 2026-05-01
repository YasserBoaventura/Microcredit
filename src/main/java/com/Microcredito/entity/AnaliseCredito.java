package com.Microcredito.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.auth.Usuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Entity
@Data 
@Builder 
public class AnaliseCredito {
	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id", unique = true, nullable = false)
    private SolicitacaoCredito solicitacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analista_id", nullable = false)
    private Usuario analista;
    
    @Column(name = "score_analisado")
    private Integer scoreAnalisado;
    
    @Column(name = "cap_renda", precision = 12, scale = 2)
    private BigDecimal capRenda;
    
    @Column(name = "comprometimento_renda", precision = 5, scale = 2)
    private BigDecimal comprometimentoRenda;
    
    @Column(name = "parecer_favoravel")
    private Boolean parecerFavoravel;
    
    @Column(name = "parecer_texto", columnDefinition = "TEXT")
    private String parecerTexto;
    
    @CreationTimestamp
    @Column(name = "data_analise", updatable = false)
    private LocalDateTime dataAnalise;
    
    @Column(name = "limite_credito_calculado", precision = 12, scale = 2)
    private BigDecimal limiteCreditoCalculado;
    
    @Column(name = "taxa_juros_ofertada", precision = 5, scale = 4)
    private BigDecimal taxaJurosOfertada;
    
    @Column(name = "restricoes_encontradas")
    private Boolean restricoesEncontradas = false;
    
    @Column(name = "descricao_restricoes", columnDefinition = "TEXT")
    private String descricaoRestricoes;


	

}

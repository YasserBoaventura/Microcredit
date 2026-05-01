package com.Microcredito.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.enums.TipoTransacao;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "extrato_transacao")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExtratoTransacao implements Serializable{

	
 
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "contrato_id", nullable = false)
	    private Contrato contrato;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(name = "tipo_transacao", nullable = false)
	    private TipoTransacao tipoTransacao;
	    
	    @Column(nullable = false, precision = 12, scale = 2)
	    private BigDecimal valor;
	    
	    @Column(name = "saldo_apos_transacao", precision = 12, scale = 2)
	    private BigDecimal saldoAposTransacao;
	    
	    @CreationTimestamp
	    @Column(name = "data_transacao", updatable = false)
	    private LocalDateTime dataTransacao;
	    
	    @Column(columnDefinition = "TEXT")
	    private String descricao;
	    
	    @Column(name = "referencia_id")
	    private Long referenciaId; // ID da parcela ou pagamento relacionado
	    
	    @Column(name = "referencia_tipo", length = 50)
	    private String referenciaTipo; // PARCELA, PAGAMENTO, AJUSTE
	
}

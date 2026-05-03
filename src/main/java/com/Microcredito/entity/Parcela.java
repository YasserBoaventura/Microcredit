package com.Microcredito.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.Microcredito.enums.StatusParcela;

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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
@Entity 
@Table(name = "parcela", indexes = {
    @Index(name = "idx_parcela_contrato", columnList = "contrato_id"),
    @Index(name = "idx_parcela_vencimento", columnList = "data_vencimento"),
    @Index(name = "idx_parcela_status", columnList = "status")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Parcela {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;
    
    @Column(name = "numero_parcela", nullable = false)
    private Integer numeroParcela;
    
    @Column(name = "valor_original", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorOriginal;
    
    @Column(name = "valor_pago", precision = 10, scale = 2)
    private BigDecimal valorPago = BigDecimal.ZERO;
    
    @Column(name = "valor_juros_multa", precision = 10, scale = 2)
    private BigDecimal valorJurosMulta = BigDecimal.ZERO;
    
    @Column(name ="valor_parcela") 
    private BigDecimal valorParcela; 
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;
    
    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusParcela status;
    
    @Column(name = "dias_atraso")
    private Integer diasAtraso = 0;
    
    @Column(name = "valor_desconto", precision = 10, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;
    
    // Relacionamento com pagamentos
    @OneToMany(mappedBy = "parcela", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagamentoParcela> pagamentoParcelas = new ArrayList <>();


}

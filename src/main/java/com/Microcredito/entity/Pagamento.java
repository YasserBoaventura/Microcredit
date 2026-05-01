package com.Microcredito.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Index;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.Microcredito.enums.MetodoPagamento;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder; 
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "pagamento", indexes = {
    @Index(name = "idx_pagamento_contrato", columnList = "contrato_id"),
    @Index(name = "idx_pagamento_data", columnList = "data_pagamento")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pagamento  implements Serializable{
	

	 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contrato_id", nullable = false)
    private Contrato contrato;
    
    @Column(name = "valor_total_pago", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotalPago;
    
    @CreationTimestamp
    @Column(name = "data_pagamento", updatable = false)
    private LocalDateTime dataPagamento;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;
    
    @Column(name = "transaction_id", length = 100)
    private String transactionId;
     
    @Column(columnDefinition = "JSON", name = "gateway_resposta")
    private String gatewayResposta;
    
    @Column(name = "status_pagamento", length = 20)
    private com.Microcredito.enums.statusPagamento statusPagamento; // PENDING, CONFIRMED, FAILED, REFUNDED
     
    @Column(name = "codigo_autorizacao", length = 50)
    private String codigoAutorizacao;
    
    @Column(name = "nsu", length = 50)
    private String nsu;
    
    // Relacionamento com parcelas
    @OneToMany(mappedBy = "pagamento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PagamentoParcela> pagamentoParcelas = new ArrayList<>();
}


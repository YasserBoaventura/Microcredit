package com.Microcredito.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "Pagamento_Parcela")
public class PagamentoParcela {
        @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "pagamento_id", nullable = false)
	    private Pagamento pagamento;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "parcela_id", nullable = false)
	    private Parcela parcela;
	    
	    @Column(name = "valor_aplicado", nullable = false, precision = 10, scale = 2)
	    private BigDecimal valorAplicado;
	    
	    @Column(name = "amortizacao", precision = 10, scale = 2)
	    private BigDecimal amortizacao;
	    
	    @Column(name = "juros", precision = 10, scale = 2)
	    private BigDecimal juros;
	    
	    @Column(name = "multa", precision = 10, scale = 2)
	    private BigDecimal multa;
	

}

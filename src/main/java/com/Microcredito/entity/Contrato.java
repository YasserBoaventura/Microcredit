package com.Microcredito.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.Microcredito.enums.StatusContrato;
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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Contrato {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitacao_id", unique = true, nullable = false)
    @JsonIgnore
    private SolicitacaoCredito solicitacao;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    private Cliente cliente;
    
    @Column(name = "numero_contrato", unique = true, nullable = false, length = 50)
    private String numeroContrato;
    
    @Column(name = "valor_total_contrato", nullable = false, precision = 12, scale = 2)
    private BigDecimal valorTotalContrato;
    
    @Column(name = "valor_parcela", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorParcela;
    
    @Column(name = "quantidade_parcelas", nullable = false)
    private Integer quantidadeParcelas;
    
    @Column(name = "taxa_juros_mensal", nullable = false, precision = 5, scale = 4)
    private BigDecimal taxaJurosMensal;
    
    @Column(name = "data_assinatura")
    private LocalDate dataAssinatura;
    
    @Column(name = "data_aceite")
    private LocalDateTime dataAceite;
    
    @Column(name = "data_vencimento_primeira", nullable = false)
    private LocalDate dataVencimentoPrimeira;
    
    @Column(name = "data_fim_contrato")
    private LocalDate dataFimContrato;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusContrato status;
    
    @Column(name = "pdf_contrato_path", length = 500)
    private String pdfContratoPath;
    
    @Column(name = "assinado_eletronicamente")
    private Boolean assinadoEletronicamente = false;
    
 
    @Column(name = "ip_assinatura", length = 45) // IPv6 suporta até 45 caracteres
    private String ipAssinatura; 
    @Column(name = "user_agent", columnDefinition = "TEXT")
    private String userAgent;
    
    // Relacionamentos
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Parcela> parcelas = new ArrayList<>();
    
    @OneToMany(mappedBy = "contrato", fetch = FetchType.LAZY)
    private List<Pagamento> pagamentos = new ArrayList<>();
    
    @OneToMany(mappedBy = "contrato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExtratoTransacao> extratos = new ArrayList<>();
    
    // Método helper
    public void addParcela(Parcela parcela) {
        parcelas.add(parcela);
        parcela.setContrato(this);
    } 

}

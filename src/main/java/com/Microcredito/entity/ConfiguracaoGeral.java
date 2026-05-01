package com.Microcredito.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.Microcredito.auth.Usuario;
import com.Microcredito.enums.TipoConfigurcao;

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
import lombok.Setter;


@Entity
@Table(name = "configuracao_geral")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfiguracaoGeral {

	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(unique = true, nullable = false, length = 100)
	    private String chave;
	    
	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String valor;
	    
	    @Column(length = 30)
	    @Enumerated(EnumType.STRING) 
	    private TipoConfigurcao tipo; // PERCENTUAL, MONETARIO, INTEIRO, TEXTO, BOOLEANO
	    
	    @Column(columnDefinition = "TEXT")
	    private String descricao;
	    
	    @UpdateTimestamp
	    @Column(name = "atualizado_em")
	    private LocalDateTime atualizadoEm;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "atualizado_por")
	    private Usuario atualizadoPor;
	}
 
	


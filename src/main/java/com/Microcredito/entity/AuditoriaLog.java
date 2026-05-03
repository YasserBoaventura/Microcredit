package com.Microcredito.entity;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.net.InetAddress;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.auth.Usuario;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "auditoria_log", indexes = {
    @Index(name = "idx_auditoria_usuario", columnList = "usuario_id, data_hora"),
    @Index(name = "idx_auditoria_entidade", columnList = "entidade, entidade_id")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaLog {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "usuario_id", nullable = false)
	    @JsonManagedReference
	    private Usuario usuario;
	    
	    @Column(nullable = false, length = 100)
	    private String acao;
	    
	    @Column(length = 50)
	    private String entidade;
	    
	    @Column(name = "entidade_id")
	    private Long entidadeId;
	  
	    @Column(name = "dados_antes", columnDefinition = "JSON")
	    private String dadosAntes;
	    
	   
	    @Column(name = "dados_depois", columnDefinition = "JSON")
	    private String dadosDepois;
	    
	    @Column(name = "ip_origem", length = 45)
	    private String ipOrigem;
	  
	    @Column(name = "user_agent", columnDefinition = "TEXT")
	    private String userAgent;
	     
	    @CreationTimestamp
	    @Column(name = "data_hora", updatable = false)
	    private LocalDateTime dataHora;
	}
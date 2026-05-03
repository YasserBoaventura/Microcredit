package com.Microcredito.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.auth.Usuario;
import com.Microcredito.enums.TipoNotificaco;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity

		@Table(name = "notificacao", indexes = {
			    @Index(name = "idx_notificacao_cliente", columnList = "cliente_id"),
			    @Index(name = "idx_notificacao_lida", columnList = "lida")
			})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Notificao {
	   @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "usuario_id")
	    @JsonManagedReference
	    private Usuario usuario;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "cliente_id")
	    @JsonManagedReference
	    private Cliente cliente;
	    
	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private TipoNotificaco tipo; 
	    
	    @Column(nullable = false, length = 200)
	    private String titulo;
	    
	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String mensagem;
	    
	    @Column(name = "lida")
	    private Boolean lida = false;
	    
	    @CreationTimestamp
	    @Column(name = "data_envio", updatable = false)
	    private LocalDateTime dataEnvio;
	    
	    @Column(name = "data_leitura")
	    private LocalDateTime dataLeitura;
	    
	    @Column(name = "destino", length = 150)
	    private String destino; // email, telefone, device_token
	    
	    @Column(name = "enviado")
	    private Boolean enviado = false;
	    
	    @Column(name = "erro_envio", columnDefinition = "TEXT")
	    private String erroEnvio;

}

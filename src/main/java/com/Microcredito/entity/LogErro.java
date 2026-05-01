package com.Microcredito.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Index;

@Entity
@Table(name = "log_erro", indexes = {
    @Index(name = "idx_log_erro_data", columnList = "data_erro")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogErro {

	
	
	    
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @Column(name = "classe_origem", length = 255)
	    private String classeOrigem;
	    
	    @Column(name = "metodo_origem", length = 100)
	    private String metodoOrigem;
	    
	    @Column(name = "mensagem_erro", columnDefinition = "TEXT")
	    private String mensagemErro;
	    
	    @Column(name = "stack_trace", columnDefinition = "TEXT")
	    private String stackTrace;
	    
	    @Column(name = "parametros", columnDefinition = "TEXT")
	    private String parametros;
	    
	    @CreationTimestamp
	    @Column(name = "data_erro", updatable = false)
	    private LocalDateTime dataErro;
	    
	    @Column(name = "resolvido")
	    private Boolean resolvido = false;
	}


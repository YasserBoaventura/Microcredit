package com.Microcredito.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.Microcredito.enums.TipoDocumento;

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


@Entity
@Table(name ="Documento")
public class Documento {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    @Column(name = "nome_arquivo", nullable = false, length = 255)
    private String nomeArquivo;
    
    @Column(name = "caminho_arquivo", nullable = false, length = 500)
    private String caminhoArquivo;
    
    @Column(name = "hash_arquivo", length = 64)
    private String hashArquivo;
    
    @CreationTimestamp
    @Column(name = "data_upload", updatable = false)
    private LocalDateTime dataUpload;
    
    @Column(name = "validado")
    private Boolean validado = false;
    
    @Column(name = "validado_em")
    private LocalDateTime validadoEm;
    
    @Column(name = "validado_por")
    private Long validadoPor;

}

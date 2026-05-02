package com.Microcredito.auth;

import org.springframework.security.core.userdetails.UserDetails;

import com.Microcredito.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@Column(name ="email", nullable = false)
	private String email; 
	private String role; 

	 
    @Column(name = "ativo")
    private Boolean ativo = true;
    
    @Column(name = "ultimo_login")
    private LocalDateTime ultimoLogin;
    
    @Column(name = "tentativas_login")
    private Integer tentativasLogin = 0;
    
    @Column(name = "bloqueado_ate")
    private LocalDateTime bloqueadoAte;
    
    @CreationTimestamp
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    
	@Override
	@JsonIgnore 
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.role));
		return authorities;
	}   
 
	@Override
	public String getPassword() {
		return password;
	}
   
	@Override
	public String getUsername() {
		return username;
	} 

    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

}

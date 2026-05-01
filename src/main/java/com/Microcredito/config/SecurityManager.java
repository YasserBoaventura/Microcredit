package com.Microcredito.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.Microcredito.auth.Usuario;

@Configuration
public class SecurityManager {

	@Autowired
	private com.Microcredito.auth.LoginRepository loginRepository;

 



	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}


    @Bean
    UserDetailsService userDetailsService() {
    return username -> {
        Optional<Usuario> usuarioOpt = loginRepository.findByUsername(username);
        
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
    }
    
    Usuario usuario = usuarioOpt.get();
    
  
    
    // Verificar se a conta não está bloqueada
    if (!usuario.isAccountNonLocked()) {
        throw new LockedException("Conta bloqueada: " + username);
    }
    
    return usuario;
};
	

}
	}



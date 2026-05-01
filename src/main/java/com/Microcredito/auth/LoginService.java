package com.Microcredito.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.Microcredito.config.JwtServiceGenerator;

@Service 
public class LoginService {

    @Autowired 
    private LoginRepository repository;
    @Autowired
    private JwtServiceGenerator jwtService;  
    @Autowired
    private AuthenticationManager authenticationManager;

    public String logar(Login login) {
        return this.gerarToken(login); 
    }

    public String gerarToken(Login login) {
        // autentica usuário e senha
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                login.getUsername(),
                login.getPassword()  
            )
        );    

        // busca usuário
        Usuario user = repository.findByUsername(login.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // gera JWT             
        return jwtService.generateToken(user);
    }
}

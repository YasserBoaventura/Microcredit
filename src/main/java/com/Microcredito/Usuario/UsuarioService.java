package com.Microcredito.Usuario;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Microcredito.auth.Usuario;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
@Transactional
public class UsuarioService {
	 private final UsuarioRepository repository;
 
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    } 
    
    public Usuario save(Usuario entity) { return repository.save(entity); }
    public Usuario findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Usuario> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Usuario findByEmail(String email) { return repository.findByEmail(email).orElse(null); }
    public void updateUltimoLogin(Long id) { repository.updateUltimoLogin(id); }
}
	


package com.Microcredito.Cliente;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Microcredito.entity.Cliente;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService { 
	  
	 private final ClienteRepository repository;
	    
	    public Cliente save(Cliente entity) { return repository.save(entity); }
	    public List<Cliente> saveAll(List<Cliente> entities) { return repository.saveAll(entities); }
	    public Cliente findById(Long id) { return repository.findById(id).orElseThrow(); }
	    public boolean existsById(Long id) { return repository.existsById(id); }
	    public List<Cliente> findAll() { return repository.findAll(); }
	    public Page<Cliente> findAll(Pageable pageable) { return repository.findAll(pageable); }
	    public void deleteById(Long id) { repository.deleteById(id); }
	    public void delete(Cliente entity) { repository.delete(entity); }
	    public void deleteAll() { repository.deleteAll(); }
	    public long count() { return repository.count(); }
	    public Cliente findByCpfCnpj(String cpf) { return repository.findByCpfCnpj(cpf).orElse(null); }
	    public Cliente findByEmail(String email) { return repository.findByEmail(email).orElse(null); }
	
}

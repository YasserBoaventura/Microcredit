package com.Microcredito.ContaBancaria;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.ContaBancaria;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor 
public class ContaBancariaService {

	  private final ContaBancariaRepository repository;
	    
    public ContaBancaria save(ContaBancaria entity) { return repository.save(entity); }
    public ContaBancaria findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<ContaBancaria> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<ContaBancaria> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId);
} 
}
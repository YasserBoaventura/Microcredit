package com.Microcredito.Endereco;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Endereco;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EndereocoRepository repository;
    
    public Endereco save(Endereco entity) { return repository.save(entity); }
    public Endereco findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Endereco> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Endereco> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }

}

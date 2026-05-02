package com.Microcredito.Documento;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Documento;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class DocumentoService {
    private final DocumentoRepository repository;
    
    public Documento save(Documento entity) { return repository.save(entity); }
    public Documento findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Documento> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Documento> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }
    public void validarDocumento(Long id) { repository.validarDocumento(id); }
 

}

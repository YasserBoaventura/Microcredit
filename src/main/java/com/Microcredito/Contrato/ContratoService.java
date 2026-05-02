package com.Microcredito.Contrato;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Microcredito.entity.Contrato;
import com.Microcredito.enums.StatusContrato;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ContratoService {

	private final ContratoRepository repository;
    
    public Contrato save(Contrato entity) { return repository.save(entity); }
    public Contrato findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Contrato> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Contrato> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }
    public Page<Contrato> findAtivos(Pageable pageable) { 
        return repository.findByStatusOrderByDataAssinaturaDesc(StatusContrato.ATIVO, pageable); 
    }

}

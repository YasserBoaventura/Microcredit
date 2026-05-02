package com.Microcredito.ContaBancaria.Pagamento;

import java.util.*;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Pagamento;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagamentoService {
	   private final PagamentoRepository repository;
	    
	    public Pagamento save(Pagamento entity) { return repository.save(entity); }
	    public Pagamento findById(Long id) { return repository.findById(id).orElseThrow(); }
	    public List<Pagamento> findAll() { return repository.findAll(); }
	    public void deleteById(Long id) { repository.deleteById(id); }
	    public List<Pagamento> findByContratoId(Long contratoId) { return repository.findByContratoId(contratoId); }

}

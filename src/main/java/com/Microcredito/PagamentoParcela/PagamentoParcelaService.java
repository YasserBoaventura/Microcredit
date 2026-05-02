package com.Microcredito.PagamentoParcela;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.PagamentoParcela;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class PagamentoParcelaService {
 
	
	 private final PagamentoParcelaRepository repository;

public PagamentoParcela save(PagamentoParcela entity) { return repository.save(entity); }
public PagamentoParcela findById(Long id) { return repository.findById(id).orElseThrow(); }
public List<PagamentoParcela> findAll() { return repository.findAll(); }
public void deleteById(Long id) { repository.deleteById(id); }
}

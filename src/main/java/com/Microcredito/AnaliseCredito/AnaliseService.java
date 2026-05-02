package com.Microcredito.AnaliseCredito;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.AnaliseCredito;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnaliseService {

private final AnaliseCreditoRepository repository;

public AnaliseCredito save(AnaliseCredito entity) { return repository.save(entity); }
public AnaliseCredito findById(Long id) { return repository.findById(id).orElseThrow(); }
public List<AnaliseCredito> findAll() { return repository.findAll(); }
public void deleteById(Long id) { repository.deleteById(id); }

public AnaliseCredito findBySolicitacaoId(Long solicitacaoId) { 
    return repository.findBySolicitacaoId(solicitacaoId).orElse(null); 
}
}
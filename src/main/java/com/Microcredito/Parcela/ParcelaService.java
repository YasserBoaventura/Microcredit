package com.Microcredito.Parcela;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Parcela;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParcelaService {

  private final ParcelaRepository repository;
     
    public Parcela save(Parcela entity) { return repository.save(entity); }
    public Parcela findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Parcela> findAll() { return repository.findAll(); }
   
    public List<Parcela> findByContratoId(Long contratoId) { return repository.findByContratoId(contratoId); }
}

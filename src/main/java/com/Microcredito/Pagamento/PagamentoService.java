package com.Microcredito.Pagamento;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.PagamentoParcela.PagamentoParcelaRepository;
import com.Microcredito.entity.Pagamento;
import com.Microcredito.entity.PagamentoParcela;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagamentoService {

private final PagamentoRepository repository;
     
    public Pagamento save(Pagamento entity) { return repository.save(entity); }
    public Pagamento findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Pagamento> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
} 

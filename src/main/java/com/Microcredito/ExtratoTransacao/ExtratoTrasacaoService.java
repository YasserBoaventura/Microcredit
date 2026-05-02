package com.Microcredito.ExtratoTransacao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.ExtratoTransacao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExtratoTrasacaoService {

	   private final ExtratoTrasacaoRepository repository;
	    
	    public ExtratoTransacao save(ExtratoTransacao entity) { return repository.save(entity); }
	    public ExtratoTransacao findById(Long id) { return repository.findById(id).orElseThrow(); }
	    public List<ExtratoTransacao> findAll() { return repository.findAll(); }
	    public void deleteById(Long id) { repository.deleteById(id); }
} 
    
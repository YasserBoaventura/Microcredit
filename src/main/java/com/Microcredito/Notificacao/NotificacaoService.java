package com.Microcredito.Notificacao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Notificao;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class NotificacaoService {

private final NotificacaoRepository repository;
    
    public Notificao save(Notificao entity) { return repository.save(entity); }
    public Notificao findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Notificao> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public void marcarComoLida(Long id) { repository.marcarComoLida(id); }
 
} 

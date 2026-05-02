package com.Microcredito.SolicitacaoCredito;

import java.util.List;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Microcredito.entity.SolicitacaoCredito;
import com.Microcredito.enums.StatusSolicitacao;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SolicitacaoCreditoService {

  private final SolicitacaoCreditoRepository repository;
    
    public SolicitacaoCredito save(SolicitacaoCredito entity) { return repository.save(entity); }
    public SolicitacaoCredito findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<SolicitacaoCredito> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<SolicitacaoCredito> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }
    public Page<SolicitacaoCredito> findPendentes(Pageable pageable) { 
        return repository.findByStatusOrderByDataSolicitacaoDesc(StatusSolicitacao.PENDENTE, pageable); 
    }
    public void atualizarStatus(Long id, StatusSolicitacao status) { repository.atualizarStatus(id, status); }

}

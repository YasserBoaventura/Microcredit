package com.Microcredito.SolicitacaoCredito;

import java.util.List;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Microcredito.Cliente.ClienteRepository;
import com.Microcredito.entity.Cliente;
import com.Microcredito.entity.SolicitacaoCredito;
import com.Microcredito.enums.StatusSolicitacao;

import DTO.SolicitacaoCreditoDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SolicitacaoCreditoService {

  private final SolicitacaoCreditoRepository repository;
  private final ClienteRepository clienteRepository; 
  
  @Transactional 
  public SolicitacaoCredito save(SolicitacaoCreditoDTO dto) {

	    Cliente cliente = clienteRepository.findById(dto.getClienteId())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	    SolicitacaoCredito solicitacao = new SolicitacaoCredito();

	    solicitacao.setCliente(cliente);
	    solicitacao.setValorSolicitado(dto.getValorSolicitado());
	    solicitacao.setQuantidadeParcelas(dto.getQuantidadeParcelas());
	    solicitacao.setTaxaJurosMensal(dto.getTaxaJurosMensal());
	    solicitacao.setFinalidade(dto.getFinalidade());

	    // regras automáticas
	    solicitacao.setStatus(StatusSolicitacao.PENDENTE);
	    solicitacao.setValorAprovado(null);
	    solicitacao.setDataResposta(null);

	    // gerar protocolo automático
	    solicitacao.setProtocolo("SOL-" + System.currentTimeMillis());
 
	    return repository.save(solicitacao);
	}
  @Transactional 
  public SolicitacaoCredito atualizar(Long id, SolicitacaoCreditoDTO dto) {

	    SolicitacaoCredito solicitacao = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

	    Cliente cliente = clienteRepository.findById(dto.getClienteId())
	            .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

	    solicitacao.setCliente(cliente);
	    solicitacao.setValorSolicitado(dto.getValorSolicitado());
	    solicitacao.setQuantidadeParcelas(dto.getQuantidadeParcelas());
	    solicitacao.setTaxaJurosMensal(dto.getTaxaJurosMensal());
	    solicitacao.setFinalidade(dto.getFinalidade());

	    // status fica controlado pelo fluxo de aprovação

	    return repository.save(solicitacao); 
	}
    public SolicitacaoCredito findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<SolicitacaoCredito> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<SolicitacaoCredito> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }
    public Page<SolicitacaoCredito> findPendentes(Pageable pageable) { 
        return repository.findByStatusOrderByDataSolicitacaoDesc(StatusSolicitacao.PENDENTE, pageable); 
    }
    public void atualizarStatus(Long id, StatusSolicitacao status) { repository.atualizarStatus(id, status); }

}

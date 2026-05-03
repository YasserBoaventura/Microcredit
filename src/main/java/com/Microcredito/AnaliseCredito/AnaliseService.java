package com.Microcredito.AnaliseCredito;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.Contrato.ContratoRepository;
import com.Microcredito.Contrato.ContratoService;
import com.Microcredito.DTO.AnaliseCreditoDTO;
import com.Microcredito.SolicitacaoCredito.SolicitacaoCreditoRepository;
import com.Microcredito.Usuario.UsuarioRepository;
import com.Microcredito.auth.Usuario;
import com.Microcredito.entity.AnaliseCredito;
import com.Microcredito.entity.SolicitacaoCredito;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnaliseService {

private final AnaliseCreditoRepository repository;
private final UsuarioRepository usuarioRepository; 
private final SolicitacaoCreditoRepository solicitacaoRepository;
private final ContratoService contratoService;  
       

@Transactional 
public AnaliseCredito save(AnaliseCreditoDTO dto) {

    SolicitacaoCredito solicitacao = solicitacaoRepository.findById(dto.getSolicitacaoId())
            .orElseThrow(() -> new RuntimeException("Solicitação não encontrada"));

    Usuario analista = usuarioRepository.findById(dto.getAnalistaId())
            .orElseThrow(() -> new RuntimeException("Analista não encontrado"));

    AnaliseCredito analise = new AnaliseCredito();

    analise.setSolicitacao(solicitacao);
    analise.setAnalista(analista);

    analise.setScoreAnalisado(dto.getScoreAnalisado());
    analise.setCapRenda(dto.getCapRenda());
    analise.setComprometimentoRenda(dto.getComprometimentoRenda());
    analise.setParecerFavoravel(dto.getParecerFavoravel());
    analise.setParecerTexto(dto.getParecerTexto());
    analise.setLimiteCreditoCalculado(dto.getLimiteCreditoCalculado());
    analise.setTaxaJurosOfertada(dto.getTaxaJurosOfertada());
    analise.setRestricoesEncontradas(dto.getRestricoesEncontradas());
    analise.setDescricaoRestricoes(dto.getDescricaoRestricoes());

    AnaliseCredito saved = repository.save(analise); 
    if (saved.getParecerFavoravel()) {
        contratoService.gerarContrato(saved);
    }
    return saved;    
}
public AnaliseCredito update(Long id, AnaliseCreditoDTO dto) {

    AnaliseCredito analise = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Análise não encontrada"));

    analise.setScoreAnalisado(dto.getScoreAnalisado());
    analise.setCapRenda(dto.getCapRenda());
    analise.setComprometimentoRenda(dto.getComprometimentoRenda());
    analise.setParecerFavoravel(dto.getParecerFavoravel());
    analise.setParecerTexto(dto.getParecerTexto());
    analise.setLimiteCreditoCalculado(dto.getLimiteCreditoCalculado());
    analise.setTaxaJurosOfertada(dto.getTaxaJurosOfertada());
    analise.setRestricoesEncontradas(dto.getRestricoesEncontradas());
    analise.setDescricaoRestricoes(dto.getDescricaoRestricoes());

    return repository.save(analise);
}
public AnaliseCredito findById(Long id) { return repository.findById(id).orElseThrow(); }
public List<AnaliseCredito> findAll() { return repository.findAll(); }
public String deleteById(Long id) { repository.deleteById(id); return "Analise iliminda com sucesso";}
 
public AnaliseCredito findBySolicitacaoId(Long solicitacaoId) { 
    return repository.findBySolicitacaoId(solicitacaoId).orElse(null); 
}
}
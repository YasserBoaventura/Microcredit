package com.Microcredito.Pagamento;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.Contrato.ContratoRepository;
import com.Microcredito.DTO.PagamentoDTO;
import com.Microcredito.ExtratoTransacao.ExtratoTrasacaoService;
import com.Microcredito.PagamentoParcela.PagamentoParcelaRepository;
import com.Microcredito.Parcela.ParcelaRepository;
import com.Microcredito.entity.Contrato;
import com.Microcredito.entity.ExtratoTransacao;
import com.Microcredito.entity.Pagamento;
import com.Microcredito.entity.PagamentoParcela;
import com.Microcredito.entity.Parcela;
import com.Microcredito.enums.MetodoPagamento;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagamentoService {

private final PagamentoRepository repository;
private final ContratoRepository contratoRepository; 
private final ParcelaRepository parcelaRepository ;
private final ExtratoTrasacaoService extratoTransacaoService;   

@Transactional
public Pagamento salvar(PagamentoDTO dto) {
Contrato contrato = contratoRepository.findById(dto.getContratoId())
            .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

    Parcela parcela = null;
    if (dto.getParcelaId() != null) {
        parcela = parcelaRepository.findById(dto.getParcelaId())
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));
    }

    Pagamento pagamento = new Pagamento();
    pagamento.setContrato(contrato);
    pagamento.setParcela(parcela);
    pagamento.setValorTotalPago(dto.getValorTotalPago());

    pagamento.setMetodoPagamento(MetodoPagamento.valueOf(dto.getMetodoPagamento()));
    pagamento.setStatusPagamento(
        com.Microcredito.enums.statusPagamento.valueOf(dto.getStatusPagamento())
    );

    pagamento.setTransactionId(dto.getTransactionId());
    pagamento.setGatewayResposta(dto.getGatewayResposta());
    pagamento.setCodigoAutorizacao(dto.getCodigoAutorizacao());
    pagamento.setNsu(dto.getNsu());
    
    Pagamento saved = repository.save(pagamento); 
    //gerar Extrato do pagamento 
    extratoTransacaoService.gerarExtratoPagamento(saved);
    return saved; 
}  
@Transactional  
public Pagamento update(Long id, PagamentoDTO dto) {

    Pagamento pagamento = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Pagamento não encontrado"));

    if (dto.getContratoId() != null) {
        Contrato contrato = contratoRepository.findById(dto.getContratoId())
                .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));
        pagamento.setContrato(contrato);
    }

    if (dto.getParcelaId() != null) {
        Parcela parcela = parcelaRepository.findById(dto.getParcelaId())
                .orElseThrow(() -> new RuntimeException("Parcela não encontrada"));
        pagamento.setParcela(parcela);
    }

    pagamento.setValorTotalPago(dto.getValorTotalPago());

    if (dto.getMetodoPagamento() != null) {
        pagamento.setMetodoPagamento(MetodoPagamento.valueOf(dto.getMetodoPagamento()));
    }

    if (dto.getStatusPagamento() != null) {
        pagamento.setStatusPagamento(
            com.Microcredito.enums.statusPagamento.valueOf(dto.getStatusPagamento())
        );
    }

    pagamento.setTransactionId(dto.getTransactionId());
    pagamento.setGatewayResposta(dto.getGatewayResposta());
    pagamento.setCodigoAutorizacao(dto.getCodigoAutorizacao());
    pagamento.setNsu(dto.getNsu());
    
    Pagamento saved = repository.save(pagamento); 
    //gerar Extrato do pagamento 
    extratoTransacaoService.gerarExtratoPagamento(saved);
    return saved;  
}

    public Pagamento findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Pagamento> findAll() { return repository.findAll(); }
    public String deleteById(Long id) { repository.deleteById(id); 
    return "Pagamento iliminado com sucesso"; 
    }
} 

package com.Microcredito.Contrato;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.*;

import com.Microcredito.Parcela.ParcelaRepository;
import com.Microcredito.Parcela.ParcelaService;
import com.Microcredito.entity.AnaliseCredito;
import com.Microcredito.entity.Contrato;
import com.Microcredito.entity.Parcela;
import com.Microcredito.enums.StatusContrato;
import com.Microcredito.enums.StatusParcela;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor 
public class ContratoService {

	private final ContratoRepository repository;
	private final ParcelaService parcelaService; 
    
	public Contrato gerarContrato(AnaliseCredito analise) {

	    Contrato contrato = new Contrato();
 
	    // Relacionamentos
	    contrato.setSolicitacao(analise.getSolicitacao());
	    contrato.setCliente(analise.getSolicitacao().getCliente());

	    // Dados principais
	    contrato.setNumeroContrato(gerarNumeroContrato());
	    contrato.setValorTotalContrato(analise.getLimiteCreditoCalculado());
	    contrato.setTaxaJurosMensal(analise.getTaxaJurosOfertada());

	    Integer parcelas = analise.getSolicitacao().getPrazoMeses();

	    if (parcelas == null || parcelas <= 0) {
	        throw new RuntimeException("Prazo inválido");
	    }
 
	    contrato.setQuantidadeParcelas(parcelas);

	    BigDecimal valorParcela = calcularParcela(
	            contrato.getValorTotalContrato(),
	            contrato.getTaxaJurosMensal(),
	            contrato.getQuantidadeParcelas()
	    );

	    contrato.setValorParcela(valorParcela);

	    // Datas
	    contrato.setDataAssinatura(LocalDate.now());
	    contrato.setDataVencimentoPrimeira(LocalDate.now().plusMonths(1));
	    contrato.setDataFimContrato(LocalDate.now().plusMonths(contrato.getQuantidadeParcelas()));

	    // Status inicial
	    contrato.setStatus(StatusContrato.ATIVO);

	    // Gerar parcelas automaticamente
	    gerarParcelas(contrato);

	    return repository.save(contrato);
	}
	
	
	@Transactional
	public Contrato atualizarContrato(Long id, AnaliseCredito analise) {

	    Contrato contrato = repository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Contrato não encontrado"));

	    // Atualiza relacionamentos (se fizer sentido no teu fluxo)
	    contrato.setSolicitacao(analise.getSolicitacao());
	    contrato.setCliente(analise.getSolicitacao().getCliente());

	    // Atualiza dados principais
	    contrato.setValorTotalContrato(analise.getLimiteCreditoCalculado());
	    contrato.setTaxaJurosMensal(analise.getTaxaJurosOfertada());

	    Integer parcelas = analise.getSolicitacao().getPrazoMeses();

	    if (parcelas == null || parcelas <= 0) {
	        throw new RuntimeException("Prazo inválido");
	    }

	    contrato.setQuantidadeParcelas(parcelas);

	    BigDecimal valorParcela = calcularParcela(
	            contrato.getValorTotalContrato(),
	            contrato.getTaxaJurosMensal(),
	            contrato.getQuantidadeParcelas()
	    );

	    contrato.setValorParcela(valorParcela);

	    // Datas (podes decidir se atualiza ou não)
	    contrato.setDataAssinatura(LocalDate.now());
	    contrato.setDataVencimentoPrimeira(LocalDate.now().plusMonths(1));
	    contrato.setDataFimContrato(LocalDate.now().plusMonths(contrato.getQuantidadeParcelas()));

	    // Status (normalmente não muda aqui)
	    contrato.setStatus(StatusContrato.ATIVO);

	    // ⚠️ IMPORTANTE: cuidado com isto
	    contrato.getParcelas().clear();
	    gerarParcelas(contrato);

	    return repository.save(contrato);
	}
	
    //gerar parcelas automaticamente 
	private void gerarParcelas(Contrato contrato) {

	    for (int i = 1; i <= contrato.getQuantidadeParcelas(); i++) {

	        Parcela parcela = new Parcela();

	        parcela.setNumeroParcela(1);;
	        parcela.setValorParcela(contrato.getValorParcela()); 
	        parcela.setValorOriginal(contrato.getValorTotalContrato()); 
	        parcela.setDataVencimento(
	                contrato.getDataVencimentoPrimeira().plusMonths(i - 1)
	        );

	        parcela.setStatus(StatusParcela.PENDENTE);
  
	        contrato.addParcela(parcela);
	    }
	}
	//calcula as parcelas
	private BigDecimal calcularParcela(BigDecimal valor, BigDecimal taxa, int meses) {

	    BigDecimal juros = valor.multiply(taxa);
	    BigDecimal total = valor.add(juros); 

	    return total.divide(BigDecimal.valueOf(meses), 2, RoundingMode.HALF_UP);
	}
	
	//gerar numero do contrato
	private String gerarNumeroContrato() {
	    return "CTR-" + System.currentTimeMillis();
	}
	
	
    public Contrato findById(Long id) { return repository.findById(id).orElseThrow(); }
    public List<Contrato> findAll() { return repository.findAll(); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public List<Contrato> findByClienteId(Long clienteId) { return repository.findByClienteId(clienteId); }
    public Page<Contrato> findAtivos(Pageable pageable) { 
        return repository.findByStatusOrderByDataAssinaturaDesc(StatusContrato.ATIVO, pageable); 
    }

}

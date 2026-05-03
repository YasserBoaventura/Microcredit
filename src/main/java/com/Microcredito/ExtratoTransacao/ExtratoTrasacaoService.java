package com.Microcredito.ExtratoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.Contrato;
import com.Microcredito.entity.ExtratoTransacao;
import com.Microcredito.entity.Pagamento;
import com.Microcredito.enums.TipoTransacao;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExtratoTrasacaoService {

	   private final ExtratoTrasacaoRepository repository;
	    
	   public void registrarMovimento(
		        Contrato contrato,
		        BigDecimal valor, 
		        TipoTransacao tipo,       // ENTRADA / SAIDA
		        String categoria, // PAGAMENTO, JUROS, MULTA...
		        String descricao
		) {
		    ExtratoTransacao extrato = new ExtratoTransacao();

		    extrato.setContrato(contrato);
		    extrato.setValor(valor);
		    extrato.setTipoTransacao(tipo);;
		    extrato.setDescricao(descricao);
		    extrato.setDataTransacao(LocalDateTime.now());
 
		    repository.save(extrato);
		} 
	   
	   
	   public void gerarExtratoPagamento(Pagamento pagamento) {
		    registrarMovimento(
		        pagamento.getContrato(),
		        pagamento.getValorTotalPago(),
		        TipoTransacao.PAGAMENTO, 
		        "PAGAMENTO",
		        "Pagamento da parcela ID: " + pagamento.getParcela().getId()
		    );
		}
	   public void gerarExtratoCredito(Contrato contrato) {
		    registrarMovimento(
		        contrato,
		        contrato.getValorTotalContrato(),
		         TipoTransacao.SAIDA,
		        "CREDITO",
		        "Liberação de crédito"
		    );
		}
	   
	   public void gerarExtratoJuros(Contrato contrato, BigDecimal valor) {
		    registrarMovimento(
		        contrato,
		        valor,
		         TipoTransacao.SAIDA,
		        "JUROS",
		        "Aplicação de juros"
		    );
		}
	 
	    public ExtratoTransacao save(ExtratoTransacao entity) { return repository.save(entity); }
	    public ExtratoTransacao findById(Long id) { return repository.findById(id).orElseThrow(); }
	    public List<ExtratoTransacao> findAll() { return repository.findAll(); }
	    public void deleteById(Long id) { repository.deleteById(id); }
} 
    
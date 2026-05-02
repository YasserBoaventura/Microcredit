package com.Microcredito.ContaBancaria.Pagamento;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Pagamento;
@Repository
public interface PagamentoRepository extends JpaRepository<Pagamento, Long>{

	 List<Pagamento> findByContratoId(Long contratoId);
	    List<Pagamento> findByContratoIdOrderByDataPagamentoDesc(Long contratoId);
	    @Query("SELECT SUM(p.valorTotalPago) FROM Pagamento p WHERE p.contrato.id = :contratoId")
	    BigDecimal sumTotalPagoPorContrato(Long contratoId);
	    @Query("SELECT p.metodoPagamento, COUNT(p), SUM(p.valorTotalPago) FROM Pagamento p GROUP BY p.metodoPagamento")
	    List<Object[]> findEstatisticasPorMetodoPagamento();
	    long countByStatusPagamento(String status);
	
}

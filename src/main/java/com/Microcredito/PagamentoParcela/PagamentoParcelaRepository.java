package com.Microcredito.PagamentoParcela;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.PagamentoParcela;
@Repository 
public interface PagamentoParcelaRepository  extends JpaRepository<PagamentoParcela,Long>{

	List<PagamentoParcela> findByPagamentoId(Long pagamentoId);
    List<PagamentoParcela> findByParcelaId(Long parcelaId);
    @Query("SELECT SUM(p.valorTotalPago) FROM Pagamento p WHERE p.parcela.id = :parcelaId")
    BigDecimal sumValorAplicadoByParcelaId(@Param("parcelaId") Long parcelaId);
} 

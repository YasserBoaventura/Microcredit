package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.math.BigDecimal;
import java.time.*;
import com.Microcredito.entity.Pagamento;

@Repository
public interface PagamentoRepository  extends JpaRepository<Pagamento, Long>{

    
    List<Pagamento> findByContratoId(Long contratoId);
    
    List<Pagamento> findByContratoIdOrderByDataPagamentoDesc(Long contratoId);
    
    @Query("SELECT p FROM Pagamento p WHERE p.dataPagamento BETWEEN :inicio AND :fim")
    List<Pagamento> findPagamentosPorPeriodo(@Param("inicio") LocalDateTime inicio, 
                                              @Param("fim") LocalDateTime fim);
    
    @Query("SELECT SUM(p.valorTotalPago) FROM Pagamento p WHERE p.contrato.id = :contratoId")
    BigDecimal sumTotalPagoPorContrato(@Param("contratoId") Long contratoId);
     
    @Query("SELECT p.metodoPagamento, COUNT(p), SUM(p.valorTotalPago) FROM Pagamento p GROUP BY p.metodoPagamento")
    List<Object[]> findEstatisticasPorMetodoPagamento();
    
    Long countByStatusPagamento(String status);
}

package repository;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.time.*;
import java.util.*;
import com.Microcredito.entity.SolicitacaoCredito;
import com.Microcredito.enums.StatusSolicitacao;

@Repository
public interface SolicitacaoCreditoRepository extends JpaRepository<SolicitacaoCredito, Long> {

	 List<SolicitacaoCredito> findByClienteId(Long clienteId);
	    
	    List<SolicitacaoCredito> findByStatus(StatusSolicitacao status);
	    
	    Page<SolicitacaoCredito> findByStatusOrderByDataSolicitacaoDesc(StatusSolicitacao status, Pageable pageable);
	    
	    @Query("SELECT s FROM SolicitacaoCredito s WHERE s.analista.id = :analistaId AND s.status = :status")
	    List<SolicitacaoCredito> findByAnalistaAndStatus(@Param("analistaId") Long analistaId, 
	                                                      @Param("status") StatusSolicitacao status);
	    
	    @Query("SELECT s FROM SolicitacaoCredito s WHERE s.dataSolicitacao BETWEEN :inicio AND :fim")
	    List<SolicitacaoCredito> findByDataSolicitacaoBetween(@Param("inicio") LocalDateTime inicio, 
	                                                           @Param("fim") LocalDateTime fim);
	    
	    @Modifying 
	    @Query("UPDATE SolicitacaoCredito s SET s.status = :status WHERE s.id = :id")
	    void atualizarStatus(@Param("id") Long id, @Param("status") StatusSolicitacao status);
	    
	    Long countByStatus(StatusSolicitacao status);
	    
	    Optional<SolicitacaoCredito> findByProtocolo(String protocolo);
}

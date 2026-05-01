package repository;

import java.util.Optional;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.*;

import org.hibernate.query.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Contrato;
import com.Microcredito.enums.StatusContrato;
@Repository 
public interface ContratoRepository extends JpaRepository<Contrato, Long>{
	 Optional<Contrato> findByNumeroContrato(String numeroContrato);
	    
	    List<Contrato> findByClienteId(Long clienteId);
	    
	    List<Contrato> findByStatus(StatusContrato status);
	    
	    @Query("SELECT c FROM Contrato c WHERE c.status = :status AND c.dataVencimentoPrimeira <= :data")
	    List<Contrato> findContratosVencidos(@Param("status") StatusContrato status, 
	                                          @Param("data") LocalDate data);
	    
	    @Query("SELECT c FROM Contrato c WHERE c.cliente.id = :clienteId AND c.status = :status")
	    List<Contrato> findByClienteAndStatus(@Param("clienteId") Long clienteId, 
	                                          @Param("status") StatusContrato status);
	    
	    @Query("SELECT SUM(c.valorTotalContrato) FROM Contrato c WHERE c.status = 'ATIVO'")
	    Double sumValorTotalAtivos();
	    
	    org.springframework.data.domain.Page<Contrato> findByStatusOrderByDataAssinaturaDesc(StatusContrato status, Pageable pageable);
	    
	    Long countByStatus(StatusContrato status);
}

package com.Microcredito.Contrato;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Contrato;
import com.Microcredito.enums.StatusContrato;
@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {

	   Optional<Contrato> findByNumeroContrato(String numeroContrato);
	    List<Contrato> findByClienteId(Long clienteId);
	    List<Contrato> findByStatus(StatusContrato status);
	    Page<Contrato> findByStatusOrderByDataAssinaturaDesc(StatusContrato status, Pageable pageable);
	    @Query("SELECT SUM(c.valorTotalContrato) FROM Contrato c WHERE c.status = 'ATIVO'")
	    Double sumValorTotalAtivos();
	    long countByStatus(StatusContrato status);
} 

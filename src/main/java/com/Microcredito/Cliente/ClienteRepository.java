package com.Microcredito.Cliente;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	  Optional<Cliente> findByCpfCnpj(String cpfCnpj);
	  Optional<Cliente> findByEmail(String email);
	  Page<Cliente> findByAtivoTrue(Pageable pageable);
	   List<Cliente> findByRendaMensalGreaterThanEqual(BigDecimal renda);
	   @Query("SELECT c FROM Cliente c WHERE c.scoreCredito >= 400 AND c.rendaMensal >= 1000")
    List<Cliente> findClientesElegiveis();
	    boolean existsByCpfCnpj(String cpfCnpj);
	    boolean existsByEmail(String email);
}

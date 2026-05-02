package com.Microcredito.Endereco;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.Endereco;
@Repository
public interface EndereocoRepository extends JpaRepository<Endereco, Long> {
	  
	List<Endereco> findByClienteId(Long clienteId);
	    List<Endereco> findByClienteIdAndPrincipalTrue(Long clienteId);
	    void deleteByClienteId(Long clienteId);
}

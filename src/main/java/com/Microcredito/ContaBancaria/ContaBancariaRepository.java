package com.Microcredito.ContaBancaria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;
import com.Microcredito.entity.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {
   
	List<ContaBancaria> findByClienteId(Long clienteId);
    Optional<ContaBancaria> findByClienteIdAndPrincipalTrue(Long clienteId);
    List<ContaBancaria> findByClienteIdAndAtivoTrue(Long clienteId);
} 
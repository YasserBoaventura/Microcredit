package com.Microcredito.Documento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;
import com.Microcredito.entity.Documento;
import com.Microcredito.enums.TipoDocumento;

@Repository
public interface DocumentoRepository extends JpaRepository<Documento, Long>{ 
	
	  List<Documento> findByClienteId(Long clienteId);
	    Optional<Documento> findByClienteIdAndTipoDocumento(Long clienteId, TipoDocumento tipo);
	    @Modifying
	    @Query("UPDATE Documento d SET d.validado = true WHERE d.id = :id")
	    void validarDocumento(Long id);
	    long countByClienteIdAndValidadoTrue(Long clienteId);

}

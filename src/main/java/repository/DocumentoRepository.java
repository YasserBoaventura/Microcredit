package repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Microcredito.entity.Documento;
import com.Microcredito.enums.TipoDocumento;

public interface DocumentoRepository extends JpaRepository<Documento, Long>{

List<Documento> findByClienteId(Long clienteId);
    
    Optional<Documento> findByClienteIdAndTipoDocumento(Long clienteId, TipoDocumento tipoDocumento);
    
    @Modifying
    @Query("UPDATE Documento d SET d.validado = true, d.validadoEm = CURRENT_TIMESTAMP, d.validadoPor = :analistaId WHERE d.id = :id")
    void validarDocumento(@Param("id") Long id, @Param("analistaId") Long analistaId);
    
    Long countByClienteIdAndValidadoTrue(Long clienteId);

}

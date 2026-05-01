package repository;
import java.awt.print.Pageable;
import java.time.LocalDateTime;
import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Microcredito.entity.AuditoriaLog;
public interface AuditoriaLogRepository extends JpaRepository<AuditoriaLog, Long> {

    List<AuditoriaLog> findByUsuarioId(Long usuarioId);
    
    List<AuditoriaLog> findByEntidadeAndEntidadeId(String entidade, Long entidadeId);
    
    @Query("SELECT a FROM AuditoriaLog a WHERE a.dataHora BETWEEN :inicio AND :fim")
    Page<AuditoriaLog> findLogsPorPeriodo(@Param("inicio") LocalDateTime inicio, 
                                           @Param("fim") LocalDateTime fim, 
                                           Pageable pageable);
    
    @Query("SELECT a.acao, COUNT(a) FROM AuditoriaLog a GROUP BY a.acao")
    List<Object[]> countAcaoPorTipo();
}
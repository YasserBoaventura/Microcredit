package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.AnaliseCredito;
import java.util.*;
@Repository
public interface AnaliseCreditoRepository extends JpaRepository<AnaliseCredito,Long> {

  Optional<AnaliseCredito> findBySolicitacaoId(Long solicitacaoId);
    List<AnaliseCredito> findByAnalistaId(Long analistaId);
    List<AnaliseCredito> findByParecerFavoravelTrue();
    boolean existsBySolicitacaoId(Long solicitacaoId);
} 

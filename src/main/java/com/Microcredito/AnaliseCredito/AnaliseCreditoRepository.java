package com.Microcredito.AnaliseCredito;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.AnaliseCredito;
@Repository   
public interface AnaliseCreditoRepository  extends JpaRepository<AnaliseCredito, Long> {
    Optional<AnaliseCredito> findBySolicitacaoId(Long solicitacaoId);
    List<AnaliseCredito> findByAnalistaId(Long analistaId);
    List<AnaliseCredito> findByParecerFavoravelTrue();
    boolean existsBySolicitacaoId(Long solicitacaoId);
}
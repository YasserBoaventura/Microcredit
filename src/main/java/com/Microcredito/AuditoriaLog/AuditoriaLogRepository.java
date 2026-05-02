package com.Microcredito.AuditoriaLog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microcredito.entity.AuditoriaLog;

@Repository
public interface AuditoriaLogRepository extends JpaRepository<AuditoriaLog, Long> {
 
}

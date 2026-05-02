package com.Microcredito.AuditoriaLog;

import java.util.List;

import org.springframework.stereotype.Service;

import com.Microcredito.entity.AuditoriaLog;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuditorioLogService {
	  private final AuditoriaLogRepository repository;
	    
	    public AuditoriaLog save(AuditoriaLog entity) { return repository.save(entity); }
	    public AuditoriaLog findById(Long id) { return repository.findById(id).orElseThrow(); }
	    public List<AuditoriaLog> findAll() { return repository.findAll(); }
	    public void deleteById(Long id) { repository.deleteById(id); }
}

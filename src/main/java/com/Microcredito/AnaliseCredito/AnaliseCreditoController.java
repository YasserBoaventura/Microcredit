package com.Microcredito.AnaliseCredito;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microcredito.DTO.AnaliseCreditoDTO;
import com.Microcredito.entity.AnaliseCredito;

import lombok.RequiredArgsConstructor;
@RestController 
@RequestMapping("/api/Analises")
@RequiredArgsConstructor
public class AnaliseCreditoController {
	
 private final AnaliseService service;
    
    @PostMapping("/save")
    public ResponseEntity<AnaliseCredito> create(@RequestBody AnaliseCreditoDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<AnaliseCredito>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}") 
    public ResponseEntity<AnaliseCredito> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping("/solicitacao/{solicitacaoId}")
    public ResponseEntity<AnaliseCredito> findBySolicitacao(@PathVariable Long solicitacaoId) {
        return ResponseEntity.ok(service.findBySolicitacaoId(solicitacaoId));
    }

@PutMapping("update/{id}")
	    public ResponseEntity<AnaliseCredito> update(@PathVariable Long id, @RequestBody AnaliseCreditoDTO entity) {
   return ResponseEntity.ok(service.update(id,entity));
	    }
	    
	    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
         return ResponseEntity.ok(service.deleteById(id));
 
    }


}

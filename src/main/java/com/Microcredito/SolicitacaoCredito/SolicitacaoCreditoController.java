package com.Microcredito.SolicitacaoCredito;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Microcredito.entity.SolicitacaoCredito;
import com.Microcredito.enums.StatusSolicitacao;

import DTO.SolicitacaoCreditoDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Solicitacoes")
@RequiredArgsConstructor 
public class SolicitacaoCreditoController {
	 private final SolicitacaoCreditoService service;
	    
    @PostMapping("/save")
    public ResponseEntity<SolicitacaoCredito> create(@RequestBody SolicitacaoCreditoDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<SolicitacaoCredito>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoCredito> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<SolicitacaoCredito>> findByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.findByClienteId(clienteId));
    }
    
    @GetMapping("/pendentes")
    public ResponseEntity<Page<SolicitacaoCredito>> findPendentes(Pageable pageable) {
        return ResponseEntity.ok(service.findPendentes(pageable));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<SolicitacaoCredito> update(@PathVariable Long id, @RequestBody SolicitacaoCreditoDTO entity) {
       
        return ResponseEntity.ok(service.atualizar(id,entity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestParam StatusSolicitacao status) {
        service.atualizarStatus(id, status);
        return ResponseEntity.ok().build();
    }

	

}

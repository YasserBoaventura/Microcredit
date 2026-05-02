package com.Microcredito.Cliente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import java.util.*;
import com.Microcredito.entity.Cliente;

import lombok.RequiredArgsConstructor;
@RestController
@RequestMapping("/api/Cliente")
@RequiredArgsConstructor
public class ClienteController {

	 private final ClienteService service;
	    
@PostMapping("/save")
public ResponseEntity<Cliente> create(@RequestBody Cliente entity) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
}

@PostMapping("/batch")
public ResponseEntity<List<Cliente>> createBatch(@RequestBody List<Cliente> entities) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.saveAll(entities));
}
 
@GetMapping("/findAll")
public ResponseEntity<List<Cliente>> findAll() {
    return ResponseEntity.ok(service.findAll());
}
    
    @GetMapping("/page")
    public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) {
        return ResponseEntity.ok(service.findAll(pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Cliente> findByCpf(@PathVariable String cpf) {
        return ResponseEntity.ok(service.findByCpfCnpj(cpf));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Long id, @RequestBody Cliente entity) {
        entity.setId(id);
        return ResponseEntity.ok(service.save(entity));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
     return ResponseEntity.ok(service.deleteById(id));  
   
    }
    
    @GetMapping("/count")
	    public ResponseEntity<Long> count() {
	        return ResponseEntity.ok(service.count());
	    }
}

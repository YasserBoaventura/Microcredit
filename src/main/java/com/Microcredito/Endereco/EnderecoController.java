package com.Microcredito.Endereco;

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

import com.Microcredito.entity.Endereco;

import DTO.EndercoDTO;
import lombok.RequiredArgsConstructor;
@RestController 
@RequestMapping("/api/Endereco")
@RequiredArgsConstructor 
public class EnderecoController {
    private final EnderecoService service;
    
    @PostMapping("/save")
    public ResponseEntity<Endereco> create(@RequestBody EndercoDTO entity) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entity));
    }
    
    @GetMapping("/findAll")
    public ResponseEntity<List<Endereco>> findAll() {
        return ResponseEntity.ok(service.findAll());
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
    
    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Endereco>> findByCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.findByClienteId(clienteId));
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<Endereco> update(@PathVariable Long id, @RequestBody EndercoDTO entity) {
       return ResponseEntity.ok(service.atualizar(id,entity));
    }  
     
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}

package com.Microcredito.Pagamento;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Microcredito.DTO.PagamentoDTO;
import com.Microcredito.entity.Pagamento;
import java.util.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/Pagamento")
@RequiredArgsConstructor
public class PagamentoController {

	private final PagamentoService pagamentoService; 
	
	@PostMapping("/save")
	public ResponseEntity<Pagamento> save(@RequestBody PagamentoDTO dto ){
		return ResponseEntity.ok(pagamentoService.salvar(dto)); 	
	}
	@PutMapping("/update/{id}")    
	public ResponseEntity<Pagamento> upadate(@PathVariable Long id, @RequestBody PagamentoDTO dto){
		return ResponseEntity.ok(pagamentoService.update(id, dto)); 
	} 
	@GetMapping("/findAll")
	public ResponseEntity<List<Pagamento>> findAll(){
		return ResponseEntity.ok(pagamentoService.findAll()); 
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		return ResponseEntity.ok(pagamentoService.deleteById(id)); 
	}
	
}

package com.Microcredito.config;
import java.util.HashMap;
import java.util.Map;



import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler {

	
	
	//TRATAMENTO DE ERROS DE VALIDATIONS
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handle01(MethodArgumentNotValidException ex) {
		Map<String, String> erros = new HashMap<>();
		for (FieldError fildError : ex.getBindingResult().getFieldErrors()) {
			erros.put(fildError.getField(), fildError.getDefaultMessage());
		}
		return new ResponseEntity<Map<String, String>>(erros, HttpStatus.BAD_REQUEST);
	} 
         
	


	//TRATAMENTO DOS DEMAIS ERROS DA APLICAÇÃO E DE REGRAS DE NEGÓCIO
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handle03(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}
}

package com.Microcredito.auth;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LoginController {

	@Autowired
	private LoginService loginService; 

	@PostMapping("/login")    
	public ResponseEntity<String> logar(@RequestBody Login login) {
     try {
		String token = loginService.logar(login);
		return new ResponseEntity<>(token, HttpStatus.OK);
		  
	}catch (AuthenticationException   e) {       
		System.out.println("Caiu aqui 1"+e.getMessage());
		 return  new ResponseEntity<>("Nao autenticado", HttpStatus.UNAUTHORIZED);
	}
     catch(Exception e){
    	    
    	 System.out.println("ErroOO "+ e.getMessage());
    	 e.printStackTrace();
    	 return new ResponseEntity("erro", HttpStatus.BAD_REQUEST);
     }
    	 
     }
}

package com.Microcredito.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.Microcredito.auth.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component  
public class JwtServiceGenerator {
	//Parâmetros para geração do token
	public static final String SECRET_KEY = "k6R6F8hU9Ztq7XlG2fY0B4QnP+9JqJ8e2g1kD0vL6qM=";

	public static final SignatureAlgorithm ALGORITMO_ASSINATURA = SignatureAlgorithm.HS256;
	public static final int HORAS_EXPIRACAO_TOKEN = 1;

	public Map<String, Object> gerarPayload(Usuario usuario){
		//AQUI VOCÊ PODE COLOCAR O QUE MAIS VAI COMPOR O PAYLOAD DO TOKEN
		
		Map<String, Object> payloadData = new HashMap<>();
		payloadData.put("username", usuario.getUsername());
		payloadData.put("id", usuario.getId());
		payloadData.put("role", usuario.getRole());
		//payloadData.put("outracoisa", "teste");
		
		return payloadData;
	}

	///////////////////////////////////////////////////////

	
	
	
	
	public String generateToken(Usuario usuario) {

		Map<String, Object> payloadData = this.gerarPayload(usuario);

		return Jwts
				.builder()
				.setClaims(payloadData)
				.setSubject(usuario.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(new Date().getTime() + 3600000 * this.HORAS_EXPIRACAO_TOKEN))
				.signWith(getSigningKey(), this.ALGORITMO_ASSINATURA)
				.compact();
	}

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(getSigningKey())
				.build()
				.parseClaimsJws(token)
				.getBody(); 
	}


	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	} 

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Key getSigningKey() {
		byte[] keyBytes = Decoders.BASE64.decode(this.SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	

   public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		
		//return claimsResolver.apply(claims);
		return claimsResolver.apply(claims);
	}

}

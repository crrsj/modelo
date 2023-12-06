package com.modelo.servico;





import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.modelo.modelos.UsuarioLogin;

@Service
public class TokenServico {
	
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String GerarToken(UsuarioLogin usuarioLogin) {
	
	try {
	    var algorithm = Algorithm.HMAC256(secret);
	    return JWT.create()
	        .withIssuer("Modelo_api")
	        .withSubject(usuarioLogin.getLogin())
	        .withExpiresAt(dataExpiração())
	        .sign(algorithm);	        
	} catch (JWTCreationException exception){
	    throw new RuntimeException("Erro ao gerar o token");
	}

}
	public String getSubject(String TokenJWT) {
	try {
	    
	   var algorithm = Algorithm.HMAC256(secret);
	   return JWT.require(algorithm)
	   .withIssuer("Modelo_api")	        
	    .build()	        
	    .verify(TokenJWT)
	    .getSubject();
	} catch (JWTVerificationException exception){
		throw new RuntimeException("Token inválido ou expirado!");
	    
	}
	
	}
	
	
	private Instant dataExpiração() {
		return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
	}
}	

package com.modelo.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.modelo.dto.Autenticacao;
import com.modelo.modelos.UsuarioLogin;
import com.modelo.servico.TokenServico;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class UsuarioLoginControle {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired
	private TokenServico tokenServico;
	
	@PostMapping
	public ResponseEntity<?>fazerLogin(@RequestBody @Valid Autenticacao autenticacao){
		var token = new UsernamePasswordAuthenticationToken(autenticacao.login(), autenticacao.senha());
		var autenticar =manager.authenticate(token);
		var tokenJWT = tokenServico.GerarToken((UsuarioLogin)autenticar.getPrincipal());
		return ResponseEntity.ok(new AutenticacaoToken(tokenJWT));
	}
}

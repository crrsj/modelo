package com.modelo.filtro;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.modelo.repositorios.UsuarioLoginRepositorio;
import com.modelo.servico.TokenServico;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@Component
public class FiltroDeSeguranca extends OncePerRequestFilter {
	
	@Autowired
	private TokenServico tokenServico;
	
	@Autowired
	private UsuarioLoginRepositorio usuarioLoginRepositorio;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
		throws ServletException, IOException {
		var tokenJWT = recuperarToken(request);
		if(tokenJWT != null) {
		var subject = tokenServico.getSubject(tokenJWT);
		var usuario =  usuarioLoginRepositorio.findByLogin(subject);
		var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		filterChain.doFilter(request,response);
		
	}

	public String recuperarToken(HttpServletRequest request) {
		var authorizationHeader = request.getHeader("Authorization");
		if(authorizationHeader != null) {
			return authorizationHeader.replace("Bearer ", "");
		}
		return null;
	}
    
}

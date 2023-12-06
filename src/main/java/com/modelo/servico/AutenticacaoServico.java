package com.modelo.servico;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.modelo.repositorios.UsuarioLoginRepositorio;


@Service
public class AutenticacaoServico implements UserDetailsService {
	
	@Autowired
    private UsuarioLoginRepositorio repositorio;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return repositorio.findByLogin(username) ;
	}

}

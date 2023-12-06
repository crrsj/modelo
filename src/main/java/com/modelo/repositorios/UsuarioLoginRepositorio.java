package com.modelo.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.modelo.modelos.UsuarioLogin;

public interface UsuarioLoginRepositorio extends JpaRepository<UsuarioLogin, UUID> {

	UserDetails findByLogin(String login);

}

package com.modelo.repositorios;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.modelo.dto.CadastroUsuario;
import com.modelo.modelos.Usuario;

@Repository
public interface UsuarioRepositorio  extends JpaRepository<Usuario, UUID> {
	@Query(value  = "Select u from usuario u where  upper(trim(u.nome))like %?1%")
	List<CadastroUsuario>buscarPorNome(String name);

}

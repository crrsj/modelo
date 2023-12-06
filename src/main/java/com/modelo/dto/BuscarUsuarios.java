package com.modelo.dto;

import java.util.UUID;

import com.modelo.modelos.Usuario;

public record BuscarUsuarios(
		
		UUID uuid,
		String nome,
		String telefone,
		String email) {

	public BuscarUsuarios(Usuario busca) {
		this(busca.getUuid(),busca.getNome(),busca.getTelefone(),busca.getEmail());
		
	}
}

package com.modelo.dto;

import java.util.UUID;

import com.modelo.modelos.Usuario;



import jakarta.validation.constraints.NotNull;

public record AtualizarUsuarios(
		@NotNull
		UUID uuid,
		String nome,
		String telefone,
		String email) {

	public AtualizarUsuarios(Usuario atualizando) {
		this(atualizando.getUuid(),atualizando.getNome(),atualizando.getTelefone(),atualizando.getEmail());
	}

}

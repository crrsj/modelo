package com.modelo.dto;

import com.modelo.modelos.Usuario;

import jakarta.validation.constraints.NotBlank;

public record CadastroUsuario(
		@NotBlank
		String nome,
		@NotBlank
		String telefone,
		@NotBlank
		String email) {

	public CadastroUsuario(Usuario cadastroUsuario) {
		this(cadastroUsuario.getNome(),cadastroUsuario.getTelefone(),cadastroUsuario.getEmail());
	}

}

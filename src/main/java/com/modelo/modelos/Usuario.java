package com.modelo.modelos;

import java.util.Collection;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.modelo.dto.AtualizarUsuarios;
import com.modelo.dto.CadastroUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity(name = "usuario")
@Table(name = "Usuario")
public class Usuario  {
	 @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
	 private  UUID uuid;
     private String nome;
     private String telefone;
     private String email;
     
	public Usuario(UUID uuid, String nome, String telefone, String email) {
		
		this.uuid = uuid;
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}

	public Usuario() {
		
	}

	public Usuario(@Valid CadastroUsuario cadastrar) {
	this.nome = cadastrar.nome();
	this.telefone = cadastrar.telefone();
	this.email = cadastrar.email();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void update(AtualizarUsuarios atualize) {
		if(atualize.nome()!= null) {
			this.nome = atualize.nome();
		}
		if(atualize.telefone()!= null) {
			this.telefone = atualize.telefone();
		}
		if(atualize.email()!= null) {
			this.email = atualize.email();
		}
	}

	
     
     
     
	
}

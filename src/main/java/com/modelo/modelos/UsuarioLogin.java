package com.modelo.modelos;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity(name = "usuariologin")
@Table(name = "Usuariologin")
public class UsuarioLogin implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private UUID uuid;
    private String login;
    private String senha;
	public UsuarioLogin(UUID uuid, String login, String senha) {
		
		this.uuid = uuid;
		this.login = login;
		this.senha = senha;
	}
	public UsuarioLogin() {
		
	}
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority("ROLE_USER"));
	}
	@Override
	public String getPassword() {
		
		return senha;
	}
	@Override
	public String getUsername() {
		
		return login;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}
	@Override
	public boolean isEnabled() {
		
		return true;
	}
    
    
}

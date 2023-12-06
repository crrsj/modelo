package com.modelo.controle;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.modelo.dto.AtualizarUsuarios;
import com.modelo.dto.BuscarUsuarios;
import com.modelo.dto.CadastroUsuario;
import com.modelo.modelos.Usuario;
import com.modelo.repositorios.UsuarioRepositorio;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@PostMapping
	@Transactional
	public ResponseEntity<CadastroUsuario>cadastro(@RequestBody @Valid CadastroUsuario cadastrar,UriComponentsBuilder uriBuilder){
	var cadastroUsuario = new Usuario(cadastrar);
	usuarioRepositorio.save(cadastroUsuario);
	var uri = uriBuilder.path("/usuario/{id}").buildAndExpand(cadastroUsuario.getUuid()).toUri();
	return ResponseEntity.created(uri).body(new CadastroUsuario(cadastroUsuario));
	}
	
	@GetMapping
	public ResponseEntity<List<BuscarUsuarios>>buscar(){
	var busca = usuarioRepositorio.findAll().stream().map(BuscarUsuarios::new).toList()	;
	return ResponseEntity.ok(busca);
	}
	@GetMapping("/{uuid}")
	public ResponseEntity<BuscarUsuarios>buscarId(@PathVariable UUID uuid){
	var buscaId = usuarioRepositorio.getReferenceById(uuid);
	return ResponseEntity.ok( new BuscarUsuarios(buscaId));
	}
	@PutMapping
	@Transactional
	public ResponseEntity<AtualizarUsuarios>atualizar(@RequestBody AtualizarUsuarios atualize){
		var atualizando =  usuarioRepositorio.getReferenceById(atualize.uuid());
		atualizando.update(atualize);
		return ResponseEntity.ok(new AtualizarUsuarios(atualizando));
	}
	@DeleteMapping("/{uuid}")
	@Transactional
	public ResponseEntity<BuscarUsuarios>delete(@PathVariable UUID uuid){
	 usuarioRepositorio.deleteById(uuid);	
	 return ResponseEntity.noContent().build();
	}
	@GetMapping("/buscaPorNome")
	public ResponseEntity<List<CadastroUsuario>> buscarPorNome(@RequestParam(name = "name")String name){
	List<CadastroUsuario> buscas = usuarioRepositorio.buscarPorNome(name.trim().toUpperCase());
	return ResponseEntity.ok(buscas);
     	}
	}


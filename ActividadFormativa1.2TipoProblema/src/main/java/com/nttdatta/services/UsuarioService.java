package com.nttdatta.services;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nttdatta.models.Usuario;
import com.nttdatta.repositories.UsuarioRepository;

@Service //se produce toda la lógica de negocio
public class UsuarioService {
	@Autowired //inyección de dependencia
	UsuarioRepository usuarioRepository;

	public void insertarUsuario(@Valid Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void editarUsuario(@Valid Usuario usuario) {
		usuarioRepository.save(usuario);
	}
	
	public void eliminarUsuario(@Valid Usuario usuario) {
		usuarioRepository.delete(usuario);
	}

	public List<Usuario> obtenerListaUsuario(){
		return usuarioRepository.findAll();
	}
	
	public List<Usuario> obtenerListaUsuarios() {

		return  usuarioRepository.findAll();
	}

	public Usuario buscarUsuarioId(Long id) {

		return usuarioRepository.findById(id).get();
	}

	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}

	public void eliminarUsuarioObjeto(Usuario usuario) {
		usuarioRepository.delete(usuario);

	}
}

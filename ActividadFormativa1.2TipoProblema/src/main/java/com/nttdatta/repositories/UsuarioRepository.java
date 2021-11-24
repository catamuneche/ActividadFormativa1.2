package com.nttdatta.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nttdatta.models.Usuario;

import java.util.List;

@Repository //es una interfaz que siempre extiende de CrudRepository para comunicarse con la DB
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	List<Usuario> findAll();

}

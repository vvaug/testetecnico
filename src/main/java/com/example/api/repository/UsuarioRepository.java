package com.example.api.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.api.domain.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	Optional<Usuario> findByEmail(String email);

}

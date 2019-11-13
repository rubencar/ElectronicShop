package com.tfg.tiendadeelectronica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNombreUsuario(String nu);
    
	boolean existsByNombreUsuario(String nombreUsuario); //Para saber si el nombre de usuario es unico
    
	boolean existsByEmail(String email); //Para saber si el email de usuario es unico
}

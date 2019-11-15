package com.tfg.tiendadeelectronica.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tfg.tiendadeelectronica.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Optional<Usuario> findByNombreUsuario(String nu);
	
	Optional<Usuario> findByDni(String dni);
    
	boolean existsByIdUsuario(Long idUsuario);
	
	boolean existsByNombreUsuario(String nombreUsuario);
    
	boolean existsByEmail(String email); 
	
	boolean existsByDni(String dni);
	
	boolean existsByTelefono1(String telefono);
}

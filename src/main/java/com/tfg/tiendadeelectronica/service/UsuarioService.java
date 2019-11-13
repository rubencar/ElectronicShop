package com.tfg.tiendadeelectronica.service;

import java.util.Optional;

import com.tfg.tiendadeelectronica.model.Usuario;

/**
 * @author rcarreterodiaz
 *
 */
public interface UsuarioService {
	
	/**
	 * @param nombreUsuario
	 * @return
	 */
	public Optional<Usuario> getByNombreUsuario(String nombreUsuario);
	
	/**
	 * @param nombreUsuario
	 * @return
	 */
	public boolean existePorNombre(String nombreUsuario);
	
	/**
	 * @param email
	 * @return
	 */
	public  boolean existePorEmail(String email);
	
	/**
	 * @param usuario
	 */
	public void guardar(Usuario usuario);
}

package com.tfg.tiendadeelectronica.service;

import java.util.List;
import java.util.Optional;

import com.tfg.tiendadeelectronica.DTO.Mensaje;
import com.tfg.tiendadeelectronica.model.Usuario;

/**
 * @author rcarreterodiaz
 *
 */
public interface UsuarioService {

	public Optional<Usuario> obtenerPorNombreUsuario(String nombreUsuario);
	
	public Optional<Usuario> obtenerPorDni(String dni);
	
	public boolean existePorIdUsuario(Long idUsuario);
	
	public boolean existePorNombreUsuario(String nombreUsuario);

	public  boolean existePorEmail(String email);
	
	public  boolean existePorDni(String dni);
	
	public  boolean existePorTelefono(String telefono);

	public void guardarUsuario(Usuario usuario);
	
	public void actualizarUsuario(Usuario usuario) throws Exception;
	
	public void eliminarUsuario(Long idUsuario);
	
	public List<Usuario> listarUsuarios();
	
	public Usuario obtenerUsuario(Long id);
	
	public String comprobarValoresNoNulos(Usuario usuario);
	
	public String existeUsuario(Usuario usuario);

}

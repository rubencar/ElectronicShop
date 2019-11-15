package com.tfg.tiendadeelectronica.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.tiendadeelectronica.model.Usuario;
import com.tfg.tiendadeelectronica.repository.UsuarioRepository;
import com.tfg.tiendadeelectronica.service.UsuarioService;
import com.tfg.tiendadeelectronica.utils.Constantes;

/**
 * @author rcarreterodiaz
 *
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
    UsuarioRepository usuarioRepository;

	@Override
    public Optional<Usuario> obtenerPorNombreUsuario(String nombreUsuario){
        return usuarioRepository.findByNombreUsuario(nombreUsuario);
    }
    
    @Override
	public Optional<Usuario> obtenerPorDni(String dni) {
		return usuarioRepository.findByDni(dni);
	}

    @Override
    public boolean existePorIdUsuario(Long idUsuario){
        return usuarioRepository.existsById(idUsuario);
    }
    
    @Override
    public boolean existePorNombreUsuario(String nombreUsuario){
        return usuarioRepository.existsByNombreUsuario(nombreUsuario);
    }

    @Override
    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }
    
    @Override
	public boolean existePorDni(String dni) {
		return usuarioRepository.existsByDni(dni);
	}

	@Override
	public boolean existePorTelefono(String telefono) {
		return usuarioRepository.existsByTelefono1(telefono);
	}

    @Override
    public void guardarUsuario(Usuario usuario){
        usuarioRepository.save(usuario);
    }

	@Override
	public void actualizarUsuario(Usuario usuario) throws Exception{
		if(usuario.getIdUsuario() != null) {
			usuarioRepository.save(usuario);
		}else {
			throw new Exception(Constantes.USUARIO_NO_VALIDO);
		}		
	}

	@Override
	public void eliminarUsuario(Long idUsuario) {
		usuarioRepository.deleteById(idUsuario);		
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}

	@Override
	public Usuario obtenerUsuario(Long id) {
		return usuarioRepository.getOne(id);
	}

	@Override
	public String comprobarValoresNoNulos(Usuario usuario) {
		
		if(StringUtils.isBlank(usuario.getNombreUsuario())){
			return Constantes.NOMBRE_USUARIO_OBLIGATORIO;
		}
		
		if(StringUtils.isBlank(usuario.getNombre())){
			return Constantes.NOMBRE_OBLIGATORIO;// HttpStatus.BAD_REQUEST;
		}
		
		if(StringUtils.isBlank(usuario.getApellidos())){
			return Constantes.APELLIDO_OBLIGATORIO;
		}
		
		if(StringUtils.isBlank(usuario.getDni())){
			return Constantes.DNI_OBLIGATORIO;
		}
		
		if(StringUtils.isBlank(usuario.getTelefono1())){
			return Constantes.TELEFONO_OBLIGATORIO;
		}
		
		if(StringUtils.isBlank(usuario.getEmail())){
			return Constantes.EMAIL_OBLIGATORIO;
		}
		
		return "";
	}

	@Override
	public String existeUsuario(Usuario usuario) {
		if(existePorNombreUsuario(usuario.getNombreUsuario())) {
			return Constantes.NOMBRE_USUARIO_YA_EXISTE;
		}
		
		if(existePorDni(usuario.getDni())) {
			return Constantes.DNI_USUARIO_YA_EXISTE;
		}
		
		if(existePorEmail(usuario.getEmail())) {
			return Constantes.EMAIL_USUARIO_YA_EXISTE;
		}
		
		if(existePorTelefono(usuario.getTelefono1())) {
			return Constantes.TELEFONO_USUARIO_YA_EXISTE;
		}
		return "";
	}
    
}

package com.tfg.tiendadeelectronica.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.tiendadeelectronica.model.Usuario;
import com.tfg.tiendadeelectronica.repository.UsuarioRepository;
import com.tfg.tiendadeelectronica.service.UsuarioService;

/**
 * @author rcarreterodiaz
 *
 */
@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getByNombreUsuario(String nu){
        return usuarioRepository.findByNombreUsuario(nu);
    }

    public boolean existePorNombre(String nu){
        return usuarioRepository.existsByNombreUsuario(nu);
    }

    public  boolean existePorEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void guardar(Usuario usuario){
        usuarioRepository.save(usuario);
    }
    
}

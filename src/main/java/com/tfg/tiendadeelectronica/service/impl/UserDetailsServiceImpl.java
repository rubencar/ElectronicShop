package com.tfg.tiendadeelectronica.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tfg.tiendadeelectronica.model.Usuario;
import com.tfg.tiendadeelectronica.security.UsuarioPrincipal;
import com.tfg.tiendadeelectronica.service.UsuarioService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
    UsuarioService usuarioService;

    /**
     * para obtener un usuario a partir de un nombre
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.getByNombreUsuario(nombreUsuario).get();
        return UsuarioPrincipal.build(usuario);
    }
    
}
